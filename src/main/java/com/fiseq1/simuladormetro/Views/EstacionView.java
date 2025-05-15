package com.fiseq1.simuladormetro.Views;

import com.fiseq1.simuladormetro.Models.Estacion;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class EstacionView extends StackPane {
    private final Estacion estacion;
    private final ImageView icono;
    private final Image IMG_OCUPADA = new Image(this.getClass().getResource("/icons/ocupada.png").toExternalForm());
    private final Image IMG_LIBRE = new Image(this.getClass().getResource("/icons/libre.png").toExternalForm());
    private final Image IMG_NEUTRAL = new Image(this.getClass().getResource("/icons/neutral.png").toExternalForm());

    public EstacionView (Estacion estacion) {
        this.estacion = estacion;
        this.icono = new ImageView(IMG_NEUTRAL);
        updateIcon();

        Text nombreEstacion = new Text(estacion.getNombre());

        this.getChildren().addAll(icono, nombreEstacion);

        this.setLayoutX(estacion.getCoordX());
        this.setLayoutY(estacion.getCoordY());
    }

    public void updateIcon() {
        if (estacion == null) return;

        if (estacion.estaOcupada()) {
            icono.setImage(IMG_OCUPADA);
        } else {
            icono.setImage(IMG_LIBRE);
        }
    }
}
