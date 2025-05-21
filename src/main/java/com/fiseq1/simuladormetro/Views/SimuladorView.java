package com.fiseq1.simuladormetro.Views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;


/**
 * Representa la ventana principal del simulador de metro.
 * <p>
 * Esta vista extiende {@link Stage} y construye una interfaz gráfica
 * sencilla con dos botones: uno para iniciar la simulación y otro para finalizarla.
 * Se organiza usando un {@link BorderPane} con un contenedor {@link HBox} en la parte inferior.
 * </p>
 */

public class SimuladorView extends Stage {
    private BorderPane root;
    private Scene scene;
    private HBox hButtons;
    private Button iniciarSimulacion, finalizarSimulacion;
    private MapaView mapa;

    public void start() {
        createGUI();
        this.scene = new Scene(root, 1280, 720);
        this.setScene(scene);
        this.setTitle("Simulador");
        this.setMinWidth(854);
        this.setMinHeight(480);
        this.show();
    }

    private void createGUI() {
        root = new BorderPane();

        // Button IniciarSimulacion
        iniciarSimulacion = new Button("Iniciar la simulacion");
        iniciarSimulacion.setPrefSize(100, 60);
        iniciarSimulacion.setWrapText(true);

        // Button FinalizarSimulacion
        finalizarSimulacion = new Button("Finalizar la simulacion");
        finalizarSimulacion.setPrefSize(100, 60);
        finalizarSimulacion.setWrapText(true);

        // HBox (Organización de los botones)
        hButtons = new HBox(iniciarSimulacion, finalizarSimulacion);
        hButtons.setSpacing(10);
        hButtons.setAlignment(Pos.BOTTOM_RIGHT);
        hButtons.setPadding(new Insets(25));

        // Mapa
        mapa = new MapaView();

        // Contenedor de elementos principales
        root.setBottom(hButtons);
        root.setCenter(mapa);

    }

    public Button getIniciarSimulacion() {
        return iniciarSimulacion;
    }

    public Button getFinalizarSimulacion() {
        return finalizarSimulacion;
    }

    public MapaView getMapaView() {
        return mapa;
    }

    public double getAncho() {
        return scene.getWidth();
    }

    public double getAlto() {
        return scene.getHeight();
    }
}
