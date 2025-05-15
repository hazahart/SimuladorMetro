package com.fiseq1.simuladormetro.Models;

/**
 * Representa una estación dentro del simulador de metro.
 * <p>
 * Cada estación tiene un nombre, coordenadas X e Y dentro del escenario visual,
 * y un estado booleano que indica si está ocupada o no.
 * Esta clase actúa como modelo de datos y no contiene lógica de interfaz gráfica.
 * </p>
 */
public class Estacion {
    private String nombre;
    private double coordX;
    private double coordY;
    private boolean ocupada;

    /**
     * Crea una estación con todos sus atributos definidos.
     *
     * @param nombre   el nombre de la estación
     * @param coordX   la coordenada X en el escenario
     * @param coordY   la coordenada Y en el escenario
     * @param ocupada  true si la estación está ocupada, false si está libre
     */
    public Estacion(String nombre, double coordX, double coordY, boolean ocupada) {
        this.nombre = nombre;
        this.coordX = coordX;
        this.coordY = coordY;
        this.ocupada = ocupada;
    }

    /**
     * Crea una estación libre por defecto (ocupada = false).
     *
     * @param nombre  el nombre de la estación
     * @param coordX  la coordenada X en el escenario
     * @param coordY  la coordenada Y en el escenario
     */
    public Estacion(String nombre, double coordX, double coordY) {
        this(nombre, coordX, coordY, false);
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public double getCoordX() {
        return coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public boolean estaOcupada() {
        return ocupada;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCoordX(double coordX) {
        this.coordX = coordX;
    }

    public void setCoordY(double coordY) {
        this.coordY = coordY;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    /**
     * Marca la estación como ocupada.
     */
    public void ocupar() {
        this.ocupada = true;
    }

    /**
     * Marca la estación como libre.
     */
    public void liberar() {
        this.ocupada = false;
    }
}
