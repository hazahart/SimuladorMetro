package com.fiseq1.simuladormetro.utils;

import com.fiseq1.simuladormetro.Controllers.SimuladorController;

/**
 * Clase generadora de números pseudoaleatorios basada en cálculos matemáticos,
 * manipulación de cadenas y generación congruencial lineal. Su rango de salida
 * se ajusta dinámicamente al valor de {@code PASAJEROS_RESTANTES}.
 * <p>
 * Esta clase forma parte del sistema de simulación de abordaje de pasajeros por estación
 * y garantiza que el valor generado nunca supere el límite restante de pasajeros.
 */
public class Pseudoaleatorio {

    private final double ALTO;
    private final double ANCHO;
    private final int valorAnterior;
    private String arrAscii = "";

    /**
     * Crea una nueva instancia del generador pseudoaleatorio.
     *
     * @param alto          Altura utilizada en los cálculos.
     * @param ancho         Ancho utilizado en los cálculos.
     * @param valorAnterior Influencia previa para generar la nueva semilla.
     */
    public Pseudoaleatorio(double alto, double ancho, int valorAnterior) {
        this.ALTO = alto;
        this.ANCHO = ancho;
        this.valorAnterior = valorAnterior;
    }

    /**
     * Ejecuta el proceso completo de generación de un número pseudoaleatorio.
     *
     * @return Valor entero generado, en el rango (1, PASAJEROS_RESTANTES).
     */
    public int generar() {
        return calcularPseudoaleatorio();
    }

    /**
     * Ejecuta internamente todo el algoritmo, desde la base matemática hasta la
     * aplicación del generador congruencial.
     *
     * @return Valor entero dentro del rango de pasajeros restantes.
     */
    private int calcularPseudoaleatorio() {
        int totalRestantes = SimuladorController.PASAJEROS_RESTANTES;

        if (totalRestantes <= 0) {
            return 1;
        }

        double PI = Math.PI;
        double base = ((ALTO * PI) + (ANCHO * PI)) * valorAnterior;
        String anchoAlto = String.valueOf(base);

        int[] asciiTemp = new int[anchoAlto.length() * 2];
        int count = 0;
        for (int i = 0; i < anchoAlto.length(); i++) {
            asciiTemp[count++] = (int) anchoAlto.charAt(i);
            asciiTemp[count++] = (int) ' ';
        }

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

    /**
     * Intercambia pares adyacentes del arreglo una sola vez (ordenamiento ciego).
     *
     * @param arr Arreglo de enteros a modificar.
     * @return El mismo arreglo, pero con pares intercambiados una vez.
     */
    private int[] burbujaCiega(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int temp = arr[i];
            arr[i] = arr[i + 1];
            arr[i + 1] = temp;
        }
        return arr;
    }

    /**
     * Genera una semilla a partir de un arreglo de cadenas numéricas,
     * sumando sus valores y agregando el valor anterior como influencia.
     *
     * @param arreglo Arreglo de cadenas que representan enteros.
     * @return Semilla entera resultante.
     */
    private int generarSemilla(String[] arreglo) {
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

    /**
     * Desordena una lista de enteros con base en una semilla,
     * simulando aleatoriedad determinista.
     *
     * @param semilla Valor que determina el patrón de mezcla.
     * @return Lista desordenada de enteros.
     */
    private int[] desordenControlado(int semilla) {
        int total = SimuladorController.PASAJEROS_RESTANTES;

        if (total <= 0) return new int[]{1};
        if (total == 1) return new int[]{0};

        int[] lista = new int[total];
        for (int i = 0; i < total; i++) {
            lista[i] = i;
        }

        for (int i = 0; i < total; i++) {
            int pos = (i * i + semilla) % total;
            if (pos == i || pos < 0 || pos >= total) continue;

            int temp = lista[i];
            lista[i] = lista[pos];
            lista[pos] = temp;
        }

        return lista;
    }

    /**
     * Aplica un generador congruencial lineal a partir de una semilla y una lista de enteros.
     * Ajusta los coeficientes dinámicamente según el tamaño actual de la lista.
     *
     * @param semilla Valor base para el generador.
     * @param lista   Lista de enteros con influencia en los coeficientes.
     * @return Número pseudoaleatorio resultante dentro del rango permitido.
     */
    private int generadorCongruenciaLineal(int semilla, int[] lista) {
        int total = SimuladorController.PASAJEROS_RESTANTES;
        if (total <= 0 || lista.length == 0) return 1;

        int indexA = Math.min(total / 4, lista.length - 1);
        int indexC = Math.min(total / 6, lista.length - 1);
        int indexX = Math.min(total / 3, lista.length - 1);

        int a = 92 + lista[indexA];
        int c = 164 + lista[indexC];
        int x = semilla * lista[indexX];

        x = (a * x + c) % (total + 1);
        x = (a * x + c) % (total + 1);

        if (x == 0) x = 1;
        return x;
    }

    /**
     * Devuelve la cadena ASCII generada internamente (si se usara).
     *
     * @return Cadena con valores ASCII concatenados.
     */
    public String getArrAscii() {
        return arrAscii;
    }

    /**
     * Establece manualmente la cadena ASCII interna.
     *
     * @param arrAscii Nueva cadena a asignar.
     */
    public void setArrAscii(String arrAscii) {
        this.arrAscii = arrAscii;
    }
}
