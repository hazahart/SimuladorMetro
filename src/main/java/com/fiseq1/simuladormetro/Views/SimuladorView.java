package com.fiseq1.simuladormetro.Views;

import com.fiseq1.simuladormetro.Controllers.SimuladorController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    private HBox hButtons, hPasajeros;
    private Button iniciarSimulacion, finalizarSimulacion, btnGrafico;
    private MapaView mapa;
    private TextField txfPasajeros;

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

        // Button para mostrar grafico
        btnGrafico = new Button("Gráfica");
        btnGrafico.setPrefSize(100, 60);
        btnGrafico.setWrapText(true);

        // TextField Pasajeros
        txfPasajeros = new TextField();
        txfPasajeros.setPrefSize(250, 50);
        txfPasajeros.setEditable(false);
        txfPasajeros.setPromptText("Pasajeros: 0/" + SimuladorController.TOTAL_PASAJEROS);

        // HBox (Caja de texto de pasajeros)
        hPasajeros = new HBox(txfPasajeros);
        hPasajeros.setSpacing(10);
        hPasajeros.setPadding(new Insets(25));

        // HBox (Organización de los botones)
        hButtons = new HBox(btnGrafico, iniciarSimulacion, finalizarSimulacion);
        hButtons.setSpacing(10);
        hButtons.setPadding(new Insets(25));

        // Contenedor inferior usando BorderPane para distribuir elementos a izquierda y derecha
        BorderPane bottomPane = new BorderPane();
        bottomPane.setLeft(hPasajeros);
        bottomPane.setRight(hButtons);

        // Mapa
        mapa = new MapaView();

        // Contenedor de elementos principales
        root.setBottom(bottomPane);
        root.setCenter(mapa);

    }

    public Button getIniciarSimulacion() {
        return iniciarSimulacion;
    }

    public Button getFinalizarSimulacion() {
        return finalizarSimulacion;
    }

    public Button getBtnGrafico() {
        return btnGrafico;
    }

    public MapaView getMapaView() {
        return mapa;
    }

    public double getAncho() {
        return scene.getWidth();
    }

    public double getAlto() {
        return scene.getHeight() * (int) (System.nanoTime() % 1000);
    }

    public TextField getTxfPasajeros() {
        return txfPasajeros;
    }

}
