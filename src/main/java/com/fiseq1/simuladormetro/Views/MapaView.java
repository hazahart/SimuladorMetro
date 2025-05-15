package com.fiseq1.simuladormetro.Views;

import com.fiseq1.simuladormetro.Models.Estacion;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.List;

/**
 * Vista que representa el mapa del simulador de metro.
 * Esta clase contiene visualmente la línea principal del recorrido del metro
 * y posiciona las estaciones en una distribución horizontal centrada.
 */
public class MapaView extends Pane {
    private final Line lineaCentral;
    private static final double MARGEN_HORIZONTAL = 50;
    private static final double ALTURA_ESTACION = 40;
    private static final double SEPARACION_LINEA = 10;


    public MapaView() {
        lineaCentral = new Line();
        lineaCentral.setStroke(Color.BLACK);
        lineaCentral.setStrokeWidth(1);

        // Establecer márgenes izquierdo y derecho
        lineaCentral.startXProperty().set(MARGEN_HORIZONTAL);
        lineaCentral.endXProperty().bind(this.widthProperty().subtract(MARGEN_HORIZONTAL));

        // Centrar verticalmente
        lineaCentral.startYProperty().bind(this.heightProperty().divide(2));
        lineaCentral.endYProperty().bind(this.heightProperty().divide(2));

        this.getChildren().add(lineaCentral);
    }

    public Line getLineaCentral() {
        return lineaCentral;
    }

    public void cargarEstaciones(List<Estacion> estaciones) {
        Runnable dibujar = () -> {
            this.getChildren().removeIf(n -> n instanceof EstacionView);

            double anchoDisponible = this.getWidth() - 2 * MARGEN_HORIZONTAL;
            double seccion = anchoDisponible / (estaciones.size() - 1);
            double y = this.getHeight() / 2;

            for (int i = 0; i < estaciones.size(); i++) {
                final int index = i;
                Platform.runLater(() -> {
                    Estacion estacion = estaciones.get(index);
                    double x = seccion * index + MARGEN_HORIZONTAL;

                    estacion.setCoordX(x);
                    estacion.setCoordY(y - 40);

                    EstacionView ev = new EstacionView(estacion);
                    ev.setLayoutX(x - ev.getBoundsInLocal().getWidth() / 2);
                    ev.setLayoutY(y - ev.getBoundsInLocal().getHeight() - SEPARACION_LINEA);

                    this.getChildren().add(ev);
                });
            }
        };


        this.widthProperty().addListener((obs, oldW, newW) -> dibujar.run());
        this.heightProperty().addListener((obs, oldH, newH) -> dibujar.run());

        this.layout(); // asegura el layout
        dibujar.run();
    }
}
