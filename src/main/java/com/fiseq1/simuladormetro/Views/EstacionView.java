package com.fiseq1.simuladormetro.Views;

import com.fiseq1.simuladormetro.Models.Estacion;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

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
    private final Image IMG_OCUPADA = new Image(this.getClass().getResource("/icons/ocupada.png").toExternalForm());
    private final Image IMG_LIBRE = new Image(this.getClass().getResource("/icons/libre.png").toExternalForm());
    private final Image IMG_NEUTRAL = new Image(this.getClass().getResource("/icons/neutral.png").toExternalForm());

    /**
     * Crea la vista visual de una estación, mostrando su estado inicial como neutral
     * y posicionándola según sus coordenadas.
     *
     * @param estacion la estación del modelo que se representa visualmente
     */
    public EstacionView (Estacion estacion) {
        this.estacion = estacion;
        this.icono = new ImageView(IMG_NEUTRAL);
        updateIcon();

        Text nombreEstacion = new Text(estacion.getNombre());

        this.getChildren().addAll(icono, nombreEstacion);

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
