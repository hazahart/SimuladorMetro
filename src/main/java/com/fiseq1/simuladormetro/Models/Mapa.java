package com.fiseq1.simuladormetro.Models;

import java.util.ArrayList;
import java.util.List;

public class Mapa {
    private final List<Estacion> estaciones;

    public Mapa() {
        estaciones = new ArrayList<>();
    }

    public void agregarEstacion(Estacion estacion) {
        estaciones.add(estacion);
    }

    public List<Estacion> getEstaciones() {
        return estaciones;
    }

    public Estacion getEstacion(int id) {
        return estaciones.get(id);
    }

    public int totalEstaciones() {
        return estaciones.size();
    }
}
