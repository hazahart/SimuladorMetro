package com.fiseq1.simuladormetro.Models;

public class Metro {
    private double cordX;
    private double cordY;

    public Metro(double cordX, double cordY) {
        this.cordX = cordX;
        this.cordY = cordY;
    }

    //Getter and Setters
    public double getCordX() {
        return cordX;
    }

    public void setCordX(double cordX) {
        this.cordX = cordX;
    }

    public double getCordY() {
        return cordY;
    }

    public void setCordY(double cordY) {
        this.cordY = cordY;
    }
}

