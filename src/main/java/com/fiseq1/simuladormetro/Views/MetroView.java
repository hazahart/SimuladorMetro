package com.fiseq1.simuladormetro.Views;

import com.fiseq1.simuladormetro.Models.Metro;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.Objects;

/**
 * Vista que representa visualmente el metro (tren) en el simulador.
 */
public class MetroView extends StackPane {
    private final Metro metro;
    private final ImageView icono;

    private static final double ICON_SIZE = 40;

    private final Image IMG_TREN = new Image(
            Objects.requireNonNull(
                    getClass().getResource("/icons/tren.png"),
                    "El icono del tren no fue encontrado"
            ).toExternalForm()
    );

    public MetroView(Metro metro) {
        this.metro = metro;

        this.icono = new ImageView(IMG_TREN);
        icono.setFitWidth(ICON_SIZE);
        icono.setFitHeight(ICON_SIZE);
        icono.setPreserveRatio(true);

        this.getChildren().add(icono);

        // Posiciona visualmente según el modelo
        this.setTranslateX(metro.getCoordX());
        this.setTranslateY(metro.getCoordY());
    }

    /**
     * Actualiza la posición del icono visual según las coordenadas del modelo.
     * Llamar a esto si se modifican las coordenadas de `metro`.
     */
    public void actualizarPosicion() {
        this.setTranslateX(metro.getCoordX());
        this.setTranslateY(metro.getCoordY());
    }

    public Metro getMetro() {
        return metro;
    }

    public ImageView getIcono() {
        return icono;
    }
}
