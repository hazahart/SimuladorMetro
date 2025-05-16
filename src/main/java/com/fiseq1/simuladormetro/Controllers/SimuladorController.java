package com.fiseq1.simuladormetro.Controllers;

import com.fiseq1.simuladormetro.Models.Estacion;
import com.fiseq1.simuladormetro.Models.Mapa;
import com.fiseq1.simuladormetro.Models.Metro;
import com.fiseq1.simuladormetro.Views.MetroView;
import com.fiseq1.simuladormetro.Views.SimuladorView;

public class SimuladorController {
    private SimuladorView simulador;
    private Mapa mapa;

    public SimuladorController(SimuladorView vista) {
        this.simulador = vista;
        this.mapa = new Mapa();
    }

    public void iniciarSimulador() {
        simulador.start();
        inicializarMapa();

        simulador.getMapaView().cargarEstaciones(mapa.getEstaciones());

        Metro metro = new Metro(0, simulador.getMapaView().getLineaCentral().getStartY() - 20);
        MetroView metroView = new MetroView(metro);
        simulador.getMapaView().agregarMetro(metroView);

        simulador.getIniciarSimulacion().setOnAction(e -> {
            System.out.println("Iniciando Simulador...");
        });
        simulador.getFinalizarSimulacion().setOnAction(e -> {
            System.out.println("Finalizando Simulador...");
        });
    }


    public void inicializarMapa() {
        mapa.agregarEstacion(new Estacion("La Laja", 100, 0));
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
}
