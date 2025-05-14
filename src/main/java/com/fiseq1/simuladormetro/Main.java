package com.fiseq1.simuladormetro;

import com.fiseq1.simuladormetro.Views.SimuladorView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        SimuladorView simulador = new SimuladorView();
        simulador.start();
    }
}
