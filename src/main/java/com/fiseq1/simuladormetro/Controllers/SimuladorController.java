package com.fiseq1.simuladormetro.Controllers;

import com.fiseq1.simuladormetro.Models.Estacion;
import com.fiseq1.simuladormetro.Models.Mapa;
import com.fiseq1.simuladormetro.Models.Metro;
import com.fiseq1.simuladormetro.Views.MetroView;
import com.fiseq1.simuladormetro.Views.SimuladorView;
import javafx.animation.*;
import javafx.util.Duration;

import java.util.List;

/**
 * Controlador principal del simulador de metro.
 * <p>
 * Se encarga de inicializar la vista {@link SimuladorView}, cargar el mapa de estaciones,
 * crear la instancia del tren ({@link Metro}) y animarlo a lo largo del recorrido.
 * </p>
 */
public class SimuladorController {
    private SimuladorView simulador;
    private Mapa mapa;
    private Metro metro;
    private MetroView metroView;

    /**
     * Crea un nuevo controlador para el simulador.
     *
     * @param vista la vista principal del simulador
     */
    public SimuladorController(SimuladorView vista) {
        this.simulador = vista;
        this.mapa = new Mapa();
    }

    /**
     * Inicializa la vista y el mapa, y configura los eventos de los botones de simulación.
     */
    public void iniciarSimulador() {
        simulador.start();
        inicializarMapa();

        simulador.getMapaView().cargarEstaciones(mapa.getEstaciones());

        inicializarMetro();

        simulador.getIniciarSimulacion().setOnAction(e -> {
            System.out.println("Iniciando Simulador...");
            animarMetro();
        });
        simulador.getFinalizarSimulacion().setOnAction(e -> {
            System.out.println("Finalizando Simulador...");
        });
    }

    /**
     * Carga manualmente las estaciones del recorrido y las agrega al modelo del mapa.
     */
    public void inicializarMapa() {
        mapa.agregarEstacion(new Estacion("La Laja", 0, 0));
        mapa.agregarEstacion(new Estacion("Galerías Celaya", 0, 0));
        mapa.agregarEstacion(new Estacion("Walmart Irrigación", 0, 0));
        mapa.agregarEstacion(new Estacion("Ferromex Celaya", 0, 0));
        mapa.agregarEstacion(new Estacion("Francisco Juárez", 0, 0));
        mapa.agregarEstacion(new Estacion("Telmex", 0, 0));
        mapa.agregarEstacion(new Estacion("Zona Celaya", 0, 0));
        mapa.agregarEstacion(new Estacion("Pemsa", 0, 0));
        mapa.agregarEstacion(new Estacion("Crespo", 0, 0));
        mapa.agregarEstacion(new Estacion("Cortazar", 0, 0));
    }

    /**
     * Inicializa el tren (objeto {@link Metro} y su vista {@link MetroView}) y lo agrega al mapa.
     */
    private void inicializarMetro() {
        double y = simulador.getMapaView().getLineaCentral().getStartY() - 20;
        metro = new Metro(0, y);
        metroView = new MetroView(metro);
        simulador.getMapaView().agregarMetro(metroView);
    }

    /**
     * Anima el tren recorriendo secuencialmente todas las estaciones del mapa.
     * Utiliza {@link TranslateTransition} y {@link PauseTransition} dentro de un {@link SequentialTransition}
     * para representar el movimiento progresivo del metro entre estaciones.
     */
    private void animarMetro() {
        List<Estacion> estaciones = mapa.getEstaciones();
        SequentialTransition secuencia = new SequentialTransition();

        for (int i = 0; i < estaciones.size(); i++) {
            Estacion estacion = estaciones.get(i);
            double destinoX = estacion.getCoordX();
            double destinoY = estacion.getCoordY() + 45;

            TranslateTransition transicion = new TranslateTransition(Duration.seconds(1.2), metroView);
            transicion.setToX(destinoX);
            transicion.setToY(destinoY);

            transicion.setOnFinished(e -> {
                metro.moverA(destinoX, destinoY);
                metroView.actualizarPosicion();
            });

            PauseTransition pausa = new PauseTransition(Duration.seconds(1));

            secuencia.getChildren().addAll(transicion, pausa);
        }

        secuencia.play();
    }
}
