package com.fiseq1.simuladormetro.Models;

public class Metro {
    private double coordX;
    private double coordY;

    public Metro(double coordX, double coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public double getCoordX() {
        return coordX;
    }

    public void setCoordX(double coordX) {
        this.coordX = coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public void setCoordY(double coordY) {
        this.coordY = coordY;
    }

    public void moverA(double x, double y) {
        this.coordX = x;
        this.coordY = y;
    }
}
