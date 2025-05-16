package com.fiseq1.simuladormetro.Views;

import com.fiseq1.simuladormetro.Models.Metro;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class MetroView extends StackPane {
    private final Metro metro;
    private final ImageView icono;

    private final Image tren = new Image(
            Objects.requireNonNull(
                    getClass().getResource("/com/fiseq1/simuladormetro/icons/tren.png"),
                    "El icono no fue encontrado"
            ).toExternalForm()
    );

    public MetroView(Metro metro) {
        this.metro = metro;

        // Crear el icono del tren
        this.icono = new ImageView(tren);
        icono.setFitWidth(40);
        icono.setFitHeight(40);

        // Contenedor del tren (puedes agregar más cosas si quieres)
        VBox contenedor = new VBox(icono);
        contenedor.setMinSize(50, 50);

        // Posicionar el tren con base en cordX y cordY
        contenedor.setTranslateX(metro.getCordX());
        contenedor.setTranslateY(metro.getCordY());

        // Agregar al StackPane
        this.getChildren().add(contenedor);
    }
}


