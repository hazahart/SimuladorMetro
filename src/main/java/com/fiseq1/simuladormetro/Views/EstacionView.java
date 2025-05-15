package com.fiseq1.simuladormetro.Views;

import com.fiseq1.simuladormetro.Models.Estacion;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Objects;

/**
 * Componente gráfico que representa visualmente una estación del simulador de metro.
 * <p>
 * Esta vista extiende {@link StackPane} y muestra:
 * <ul>
 *     <li>Un ícono que representa el estado de la estación (ocupada, libre o neutral).</li>
 *     <li>El nombre de la estación como texto.</li>
 * </ul>
 * La vista se posiciona automáticamente según las coordenadas definidas en el modelo {@link Estacion}.
 * El ícono se puede actualizar con {@link #updateIcon()} para reflejar el estado actual.
 * </p>
 */

public class EstacionView extends StackPane {
    private final Estacion estacion;
    private final ImageView icono;

    private final Image IMG_OCUPADA = new Image(Objects.requireNonNull(
            getClass().getResource("/com/fiseq1/simuladormetro/icons/ocupada.png"), "Icono ocupada.png no encontrado").toExternalForm());

    private final Image IMG_LIBRE = new Image(Objects.requireNonNull(
            getClass().getResource("/com/fiseq1/simuladormetro/icons/libre.png"), "Icono libre.png no encontrado").toExternalForm());

    private final Image IMG_NEUTRAL = new Image(Objects.requireNonNull(
            getClass().getResource("/com/fiseq1/simuladormetro/icons/neutral.png"), "Icono neutral.png no encontrado").toExternalForm());

    /**
     * Crea la vista visual de una estación, mostrando su estado inicial como neutral
     * y posicionándola según sus coordenadas.
     *
     * @param estacion la estación del modelo que se representa visualmente
     */
    public EstacionView(Estacion estacion) {
        this.estacion = estacion;

        // Crear el ImageView con imagen neutral por defecto
        this.icono = new ImageView(IMG_NEUTRAL);

        // Establecer tamaño fijo (ajústalo como prefieras)
        icono.setFitWidth(40);
        icono.setFitHeight(40);
        icono.setPreserveRatio(true);

        // Crear texto con nombre de la estación
        Text nombreEstacion = new Text(estacion.getNombre());

        // VBox para estaciones y nombre
        VBox vEstacion = new VBox(icono, nombreEstacion);


        // Añadir ícono y texto al StackPane
        this.getChildren().addAll(vEstacion);

        // Posicionar el StackPane según coordenadas del modelo
        this.setLayoutX(estacion.getCoordX());
        this.setLayoutY(estacion.getCoordY());
    }

    /**
     * Actualiza el ícono de la estación para reflejar si está ocupada o libre.
     */
    public void updateIcon() {
        if (estacion == null) return;

        if (estacion.estaOcupada()) {
            icono.setImage(IMG_OCUPADA);
        } else {
            icono.setImage(IMG_LIBRE);
        }
    }
}
