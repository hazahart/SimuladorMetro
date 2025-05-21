package com.fiseq1.simuladormetro.Views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Map;

/**
 * Vista encargada de mostrar un gráfico de barras con los pasajeros subidos por estación.
 */
public class GraficoPasajerosView {

    private final Map<String, Integer> datos;
    private BorderPane bdpGrafico;

    public GraficoPasajerosView(Map<String, Integer> datos) {
        this.datos = datos;
    }

    public void mostrar() {
        CategoryAxis ejeX = new CategoryAxis();
        NumberAxis ejeY = new NumberAxis();
        ejeX.setLabel("Estación");
        ejeY.setLabel("Pasajeros");

        BarChart<String, Number> grafico = new BarChart<>(ejeX, ejeY);
        grafico.setTitle("No. pasajeros que suben");
        grafico.setLegendVisible(false);
        grafico.setCategoryGap(10);
        grafico.setBarGap(3);
        grafico.setAnimated(false);
        grafico.setPrefSize(700, 600);

        // Agregar datos a la serie
        XYChart.Series<String, Number> serie = new XYChart.Series<>();
        for (Map.Entry<String, Integer> entry : datos.entrySet()) {
            serie.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }
        grafico.getData().add(serie);

        // Rotar etiquetas del eje X
        ejeX.setTickLabelRotation(-45);

        // Estilo de las barras
        grafico.setStyle("-fx-background-color: transparent;");
        grafico.lookupAll(".default-color0.chart-bar").forEach(bar -> bar.setStyle("-fx-bar-fill: #2980b9;"));

        // Crear tabla con GridPane
        GridPane tabla = new GridPane();
        tabla.setHgap(10);
        tabla.setVgap(5);
        tabla.setPadding(new Insets(10));

        Label encabezado1 = new Label("Estación");
        Label encabezado2 = new Label("Pasajeros");
        encabezado1.setStyle("-fx-font-weight: bold;");
        encabezado2.setStyle("-fx-font-weight: bold;");
        tabla.add(encabezado1, 0, 0);
        tabla.add(encabezado2, 1, 0);

        int fila = 1;
        int total = 0;
        for (Map.Entry<String, Integer> entry : datos.entrySet()) {
            tabla.add(new Label(entry.getKey()), 0, fila);
            tabla.add(new Label(String.valueOf(entry.getValue())), 1, fila);
            total += entry.getValue();
            fila++;
        }

        // Fila de total
        Label totalLabel = new Label("Total");
        totalLabel.setStyle("-fx-font-weight: bold;");
        tabla.add(totalLabel, 0, fila);
        Label totalValor = new Label(String.valueOf(total));
        totalValor.setStyle("-fx-font-weight: bold;");
        tabla.add(totalValor, 1, fila);

        // Botón cerrar
        Button btnCerrar = new Button("Regresar");
        btnCerrar.setOnAction(e -> ((Stage) btnCerrar.getScene().getWindow()).close());
        HBox hButtons = new HBox(btnCerrar);
        hButtons.setSpacing(10);
        hButtons.setPadding(new Insets(25));
        BorderPane bdpBottom = new BorderPane();
        bdpBottom.setRight(hButtons);

        VBox tablaConBoton = new VBox(tabla);
        tablaConBoton.setAlignment(Pos.CENTER_LEFT);
        tablaConBoton.setSpacing(20);
        tablaConBoton.setPadding(new Insets(20));
        tablaConBoton.setPrefWidth(320);

        // Layout final: tabla + gráfico
        HBox contenido = new HBox();
        contenido.getChildren().addAll(tablaConBoton, grafico);
        contenido.setSpacing(30);
        contenido.setPadding(new Insets(20));

        bdpGrafico = new BorderPane();
        bdpGrafico.setCenter(contenido);
        bdpGrafico.setBottom(bdpBottom);

        Scene escena = new Scene(bdpGrafico, 1280, 720);
        Stage ventana = new Stage();
        ventana.setTitle("Resumen de Pasajeros");
        ventana.setScene(escena);
        ventana.show();
        ventana.setWidth(escena.getWidth());
        ventana.setHeight(escena.getHeight());
    }
}
