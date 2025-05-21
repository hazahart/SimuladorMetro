package com.fiseq1.simuladormetro.utils;

import com.fiseq1.simuladormetro.Controllers.SimuladorController;

public class Pseudoaleatorio {

    private final double ALTO;
    private final double ANCHO;
    private final int TOTAL_PASAJEROS = SimuladorController.TOTAL_PASAJEROS;
    private String arrAscii = "";
    private final int valorAnterior;

    public Pseudoaleatorio(double alto, double ancho) {
        this(alto, ancho, 0);
    }

    public Pseudoaleatorio(double alto, double ancho, int valorAnterior) {
        this.ALTO = alto;
        this.ANCHO = ancho;
        this.valorAnterior = valorAnterior;
    }

    private int calcularPseudoaleatorio() {
        double PI = Math.PI;
        double base = ((ALTO * PI) + (ANCHO * PI)) * valorAnterior;
        String anchoAlto = String.valueOf(base);
        System.out.println("Base modificada con valorAnterior (" + valorAnterior + "): " + anchoAlto);

        // Convertir a valores ASCII
        int[] asciiTemp = new int[anchoAlto.length() * 2];
        int count = 0;
        for (int i = 0; i < anchoAlto.length(); i++) {
            asciiTemp[count++] = (int) anchoAlto.charAt(i);
            asciiTemp[count++] = (int) ' ';
        }

        // Separar en pares de 2 dígitos
        int tamArreglo = count / 2;
        int[] listaAscii = new int[tamArreglo];
        for (int i = 0; i < tamArreglo; i++) {
            String chunk = "" + (char) asciiTemp[i * 2] + (char) asciiTemp[i * 2 + 1];
            try {
                listaAscii[i] = Integer.parseInt(chunk.trim());
            } catch (NumberFormatException e) {
                listaAscii[i] = 32;
            }
        }

        int[] arreglo = burbujaCiega(listaAscii);
        String[] arregloStr = new String[arreglo.length];
        for (int i = 0; i < arreglo.length; i++) {
            arregloStr[i] = String.valueOf(arreglo[i]);
        }

        int semilla = generarSemilla(arregloStr);
        int[] lista = desordenControlado(semilla);
        return generadorCongruenciaLineal(semilla, lista);
    }

    public int[] burbujaCiega(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int temp = arr[i];
            arr[i] = arr[i + 1];
            arr[i + 1] = temp;
        }
        return arr;
    }

    public int generarSemilla(String[] arreglo) {
        int semilla = 0;
        for (String s : arreglo) {
            try {
                semilla += Integer.parseInt(s);
            } catch (NumberFormatException e) {
                semilla += 32;
            }
        }
        semilla += valorAnterior;
        return semilla;
    }

    public int[] desordenControlado(int semilla) {
        int[] lista = new int[TOTAL_PASAJEROS];
        for (int i = 0; i < TOTAL_PASAJEROS; i++) {
            lista[i] = i;
        }

        for (int i = 0; i < TOTAL_PASAJEROS; i++) {
            int pos = (i * i + semilla) % TOTAL_PASAJEROS;
            if (pos == 0) pos = 1;
            int temp = lista[i];
            lista[i] = lista[pos];
            lista[pos] = temp;
        }

        return lista;
    }

    public int generadorCongruenciaLineal(int semilla, int[] lista) {
        int a = 92 + lista[TOTAL_PASAJEROS / 4];
        int c = 164 + lista[TOTAL_PASAJEROS / 6];
        int x = semilla * lista[TOTAL_PASAJEROS / 3];

        x = (a * x + c) % (TOTAL_PASAJEROS + 1);
        x = (a * x + c) % (TOTAL_PASAJEROS + 1);

        if (x == 0) x = 1;
        return x;
    }

    public int generar() {
        return calcularPseudoaleatorio();
    }

    public String getArrAscii() {
        return arrAscii;
    }

    public void setArrAscii(String arrAscii) {
        this.arrAscii = arrAscii;
    }
}
