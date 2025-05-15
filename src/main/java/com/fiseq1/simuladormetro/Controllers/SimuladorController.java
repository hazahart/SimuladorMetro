package com.fiseq1.simuladormetro.Controllers;

import com.fiseq1.simuladormetro.Views.SimuladorView;

public class SimuladorController {
    private SimuladorView simulador;
    public SimuladorController(SimuladorView vista) {
        this.simulador = vista;
    }

    public void iniciarSimulador() {
        simulador.start();
        simulador.getIniciarSimulacion().setOnAction(e -> {
            System.out.println("Iniciando Simulador...");
        });
        simulador.getFinalizarSimulacion().setOnAction(e -> {
            System.out.println("Finalizando Simulador...");
        });
    }
}
