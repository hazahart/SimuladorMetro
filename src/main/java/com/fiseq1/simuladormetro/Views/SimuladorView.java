package com.fiseq1.simuladormetro.Views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

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

    public void start() {
        createGUI();
        this.scene = new Scene(root, 800, 600);
        this.setScene(scene);
        this.setTitle("Simulador");
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

        // Contenedor de elementos principales
        root.setBottom(hButtons);
    }

    public Button getIniciarSimulacion() {
        return iniciarSimulacion;
    }

    public Button getFinalizarSimulacion() {
        return finalizarSimulacion;
    }
}
