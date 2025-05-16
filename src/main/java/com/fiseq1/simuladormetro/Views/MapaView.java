package com.fiseq1.simuladormetro.Views;

import com.fiseq1.simuladormetro.Models.Estacion;
import javafx.scene.Group;
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
    private final Group grupoEstaciones;
    private static final double MARGEN_HORIZONTAL = 50;
    private static final double ALTURA_ESTACION = 40;
    private static final double SEPARACION_LINEA = 25;

    public MapaView() {
        lineaCentral = new Line();
        lineaCentral.setStroke(Color.BLACK);
        lineaCentral.setStrokeWidth(1);

        // Posición horizontal
        lineaCentral.startXProperty().set(MARGEN_HORIZONTAL);
        lineaCentral.endXProperty().bind(this.widthProperty().subtract(MARGEN_HORIZONTAL));

        // Centrado vertical
        lineaCentral.startYProperty().bind(this.heightProperty().divide(2));
        lineaCentral.endYProperty().bind(this.heightProperty().divide(2));

        grupoEstaciones = new Group();
        this.getChildren().addAll(lineaCentral, grupoEstaciones);

        // Redibujar cuando cambie el tamaño
        this.widthProperty().addListener((obs, oldW, newW) -> redibujar());
        this.heightProperty().addListener((obs, oldH, newH) -> redibujar());
    }

    private List<Estacion> estacionesActuales;

    public void cargarEstaciones(List<Estacion> estaciones) {
        this.estacionesActuales = estaciones;
        redibujar();
    }

    private void Imprimir_tren (){

    }

    private void redibujar() {
        if (estacionesActuales == null || estacionesActuales.isEmpty()) return;

        grupoEstaciones.getChildren().clear();

        double anchoDisponible = this.getWidth() - 2 * MARGEN_HORIZONTAL;
        double seccion = anchoDisponible / (estacionesActuales.size() - 1);
        double y = this.getHeight() / 2;

        for (int i = 0; i < estacionesActuales.size(); i++) {
            Estacion estacion = estacionesActuales.get(i);
            double x = seccion * i + MARGEN_HORIZONTAL;

            estacion.setCoordX(x);
            estacion.setCoordY(y - ALTURA_ESTACION - SEPARACION_LINEA);

            EstacionView ev = new EstacionView(estacion);
            ev.setLayoutX(x - ev.getBoundsInLocal().getWidth() / 2);
            ev.setLayoutY(y - ALTURA_ESTACION - SEPARACION_LINEA);

            grupoEstaciones.getChildren().add(ev);
        }
    }

    public Line getLineaCentral() {
        return lineaCentral;
    }

    public void agregarMetro(MetroView metroView) {
        this.getChildren().add(metroView);
    }

}
