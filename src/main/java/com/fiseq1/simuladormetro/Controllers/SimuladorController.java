package com.fiseq1.simuladormetro.Controllers;

import com.fiseq1.simuladormetro.Models.Estacion;
import com.fiseq1.simuladormetro.Models.Mapa;
import com.fiseq1.simuladormetro.Models.Metro;
import com.fiseq1.simuladormetro.Views.EstacionView;
import com.fiseq1.simuladormetro.Views.MetroView;
import com.fiseq1.simuladormetro.Views.SimuladorView;
import javafx.animation.*;
import javafx.util.Duration;

import java.util.List;

/**
 * Controlador del simulador de metro.
 * <p>
 * Esta clase es responsable de coordinar la lógica principal del simulador:
 * inicializa la interfaz gráfica, carga las estaciones del mapa, crea el tren
 * y anima su recorrido por las estaciones. A medida que el tren se desplaza,
 * se actualizan dinámicamente los estados visuales y lógicos de las estaciones
 * (ocupadas o libres) en tiempo real.
 * </p>
 * <p>
 * El controlador también administra la interacción con la vista principal
 * ({@link SimuladorView}) y sus componentes, como el mapa de estaciones y los botones de control.
 * </p>
 */
public class SimuladorController {
    private SimuladorView simulador;
    private Mapa mapa;
    private Metro metro;
    private MetroView metroView;
    private SequentialTransition secuencia;


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
            simulador.getIniciarSimulacion().setDisable(true);
        });
        simulador.getFinalizarSimulacion().setOnAction(e -> {
            System.out.println("Finalizando Simulador...");
            finalizarSimulacion();
            simulador.getIniciarSimulacion().setDisable(false);
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
     * Anima el recorrido del metro desde la primera hasta la última estación.
     * <p>
     * Utiliza una {@link SequentialTransition} para encadenar una serie de
     * {@link TranslateTransition} (movimiento del tren hacia cada estación)
     * y {@link PauseTransition} (tiempo de espera en cada estación).
     * Durante la animación, se actualiza la posición del modelo {@link Metro}
     * y se notifican visualmente los cambios de ocupación de estaciones.
     * </p>
     */
    private void animarMetro() {
        List<Estacion> estaciones = mapa.getEstaciones();
        secuencia = new SequentialTransition();

        for (Estacion estacion : estaciones) {
            EstacionView vista = simulador.getMapaView().getVistaDeEstacion(estacion);
            if (vista == null) continue;

            TranslateTransition transicion = new TranslateTransition(Duration.seconds(1), metroView);
            transicion.setToX(estacion.getCoordX());
            transicion.setToY(estacion.getCoordY() + 45);

            transicion.setOnFinished(e -> {
                metro.moverA(estacion.getCoordX(), estacion.getCoordY() + 45);
                metroView.actualizarPosicion();
                actualizarEstadosEstaciones();
                System.out.println("Coordenadas estacion: " + estacion.getCoordX() + "," + estacion.getCoordY());
                System.out.println("Coordenadas metro: " + metro.getCoordX() + "," + metro.getCoordY());
            });

            PauseTransition pausa = new PauseTransition(Duration.seconds(3));
            secuencia.getChildren().addAll(transicion, pausa);
        }

        secuencia.play();
    }

    /**
     * Actualiza el estado de ocupación de cada estación.
     * <p>
     * Compara la posición actual del metro (coordenadas del modelo) con la de cada estación.
     * Si el metro se encuentra en una estación (coordenadas coinciden dentro de un margen),
     * se marca como ocupada; de lo contrario, se marca como libre.
     * Después, se actualiza visualmente cada ícono de estación con {@code updateIcon()}.
     * </p>
     */
    private void actualizarEstadosEstaciones() {
        List<Estacion> estaciones = mapa.getEstaciones();

        for (Estacion estacion : estaciones) {
            double x = estacion.getCoordX();
            double y = estacion.getCoordY() + 45;

            boolean estaEnEstacion = metro.getCoordX() == x || metro.getCoordY() + 45 == y;

            if (estaEnEstacion) {
                estacion.ocupar();
            } else {
                estacion.liberar();
            }
        }
        simulador.getMapaView().actualizarVistasEstaciones();
    }

    /**
     * Finaliza la simulación del metro.
     * <p>
     * Detiene la animación actual, libera todas las estaciones, actualiza los íconos
     * y reposiciona el tren en la primera estación.
     * </p>
     */
    private void finalizarSimulacion() {
        // Detener la animación si está en curso
        if (secuencia != null && secuencia.getStatus() == Animation.Status.RUNNING) {
            secuencia.stop();
        }

        // Liberar todas las estaciones
        for (Estacion estacion : mapa.getEstaciones()) {
            estacion.liberar();
        }

        // Actualizar visualmente las estaciones
        simulador.getMapaView().actualizarVistasEstaciones();

        // Reposicionar el tren al inicio (primera estación: La Laja)
        Estacion inicio = mapa.getEstaciones().get(0);
        double x = inicio.getCoordX();
        double y = inicio.getCoordY() + 45;

        metro.moverA(x, y);
        metroView.actualizarPosicion();

        System.out.println("Simulación finalizada. Tren reiniciado en: " + inicio.getNombre());
    }
}
