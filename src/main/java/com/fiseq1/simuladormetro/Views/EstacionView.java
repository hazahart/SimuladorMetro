package com.fiseq1.simuladormetro.Views;

import com.fiseq1.simuladormetro.Models.Estacion;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class EstacionView extends StackPane {
    private Estacion estacion;
    private final ImageView icono;

    public EstacionView (Estacion estacion) {
        this.estacion = estacion;
        this.icono = new ImageView();
        updateIcon();

        Text nombreEstacion = new Text(estacion.getNombre());

        this.getChildren().addAll(icono, nombreEstacion);

        this.setLayoutX(estacion.getCoordX());
        this.setLayoutY(estacion.getCoordY());
    }

    public void updateIcon() {
        if (estacion == null) return;
    }
}
