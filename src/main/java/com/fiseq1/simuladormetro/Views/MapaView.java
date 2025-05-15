package com.fiseq1.simuladormetro.Views;

import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Vista que representa el mapa del simulador de metro.
 * <p>
 * Esta clase contiene visualmente la línea principal del recorrido del metro.
 * La línea se ajusta automáticamente al ancho disponible y respeta el padding lateral.
 * En versiones futuras se pueden agregar estaciones, trenes o rutas adicionales.
 * </p>
 */
public class MapaView extends StackPane {
    private final Line lineaCentral;

    /**
     * Crea la vista del mapa con una línea central horizontal ajustable.
     */
    public MapaView() {
        this.setPadding(new Insets(25)); // espacio alrededor del mapa

        lineaCentral = new Line(0, 0, 0, 0);
        lineaCentral.setStroke(Color.BLACK);
        lineaCentral.setStrokeWidth(1);
        lineaCentral.endXProperty().bind(this.widthProperty().divide(1.1));

        this.getChildren().add(lineaCentral);
    }

    /**
     * Devuelve la línea principal del mapa.
     *
     * @return la línea central que representa el recorrido del metro
     */
    public Line getLineaCentral() {
        return lineaCentral;
    }
}
