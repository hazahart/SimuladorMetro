package com.fiseq1.simuladormetro.utils

import com.fiseq1.simuladormetro.Controllers.SimuladorController

/**
 * Clase generadora de números pseudoaleatorios basada en transformaciones matemáticas
 * con influencia del valor anterior generado y dimensiones proporcionadas.
 *
 * El número generado está limitado a un rango de 1 a [TOTAL_PASAJEROS] (40 por defecto).
 * Combina manipulación de cadenas ASCII, ordenamientos simples, una semilla influenciada por el
 * valor anterior y un generador congruencial lineal.
 *
 * @param alto Altura usada como parte del cálculo base para la generación.
 * @param ancho Ancho usado como parte del cálculo base para la generación.
 * @param valorAnterior Valor numérico usado como influencia en la semilla. Por defecto es 0.
 */
class Pseudoaleatorio(var alto: Double, var ancho: Double, var valorAnterior: Int = 0) {

    /** Valor constante de altura para uso interno. */
    val ALTO = alto

    /** Valor constante de ancho para uso interno. */
    val ANCHO = ancho

    /** Valor de PI utilizado en cálculos base. */
    val PI = Math.PI

    /** Número máximo de pasajeros (límite superior del número aleatorio). */
    val TOTAL_PASAJEROS = SimuladorController.TOTAL_PASAJEROS

    /** Cadena intermedia con valores ASCII (actualmente sin uso externo). */
    var arrAscii: String = ""

    /** Resultado generado internamente. */
    private var resultado: Int = 0

    /**
     * Ejecuta todo el proceso de generación del número pseudoaleatorio:
     * - Calcula una base usando PI, alto, ancho y el valor anterior.
     * - Convierte la base en una cadena de caracteres ASCII.
     * - Aplica ordenamiento básico (burbuja ciega).
     * - Genera una semilla.
     * - Desordena una lista de forma controlada.
     * - Aplica un generador congruencial lineal.
     *
     * @return Número pseudoaleatorio resultante en el rango (1, TOTAL_PASAJEROS).
     */
    private fun calcularPseudoaleatorio(): Int {
        val base = ((ALTO * PI) + (ANCHO * PI)) * valorAnterior
        val anchoAlto = "$base"
        println("Base modificada con valorAnterior ($valorAnterior): $anchoAlto")

        val cadenaAscii = StringBuilder()
        for (char in anchoAlto) {
            cadenaAscii.append(char.code).append(' '.code)
        }

        val cadenaAsciiStr = cadenaAscii.toString()
        val listaAscii = cadenaAsciiStr.chunked(2).map { it.toInt() }.toMutableList()

        val arreglo = burbujaCiega(listaAscii)
        val arregloStr = arreglo.map { it.toString() }.toTypedArray()
        val semilla = generarSemilla(arregloStr)
        val lista = desordenControlado(semilla)
        resultado = generadorCongruenciaLineal(semilla, lista)
        return resultado
    }

    /**
     * Aplica un ordenamiento mínimo (burbuja ciega) intercambiando pares adyacentes una sola vez.
     *
     * @param arr Lista mutable de enteros a reordenar.
     * @return Lista modificada tras la operación.
     */
    fun burbujaCiega(arr: MutableList<Int>): MutableList<Int> {
        for (i in 0 until arr.size - 1) {
            val temp = arr[i]
            arr[i] = arr[i + 1]
            arr[i + 1] = temp
        }
        return arr
    }

    /**
     * Genera una semilla entera sumando los valores ASCII de los elementos del arreglo
     * y añadiendo el [valorAnterior] como parte del cálculo.
     *
     * @param arreglo Arreglo de cadenas numéricas derivadas de caracteres ASCII.
     * @return Valor entero que servirá como semilla para el algoritmo.
     */
    fun generarSemilla(arreglo: Array<String>): Int {
        var semilla = 0
        for (car in arreglo) {
            val codigo = car.toIntOrNull() ?: 32
            semilla += codigo
        }
        semilla += valorAnterior
        return semilla
    }

    /**
     * Reorganiza una lista de enteros de forma controlada con base en la semilla,
     * simulando una mezcla pseudoaleatoria pero determinista.
     *
     * @param semilla Valor entero que influye en el patrón de desordenamiento.
     * @return Lista de enteros reorganizada.
     */
    fun desordenControlado(semilla: Int): IntArray {
        val lista = IntArray(TOTAL_PASAJEROS) { it + 1 }
        for (i in 0 until TOTAL_PASAJEROS) {
            lista[i] = i
        }
        for (i in 0 until TOTAL_PASAJEROS) {
            var pos = (i * i + semilla) % TOTAL_PASAJEROS
            if (pos == 0) pos = 1
            val temp = lista[i]
            lista[i] = lista[pos]
            lista[pos] = temp
        }
        return lista
    }

    /**
     * Generador congruencial lineal que usa la semilla y una lista
     * para obtener un número dentro del rango permitido.
     *
     * @param semilla Valor inicial del generador.
     * @param lista Lista de enteros usada para calcular coeficientes dinámicos.
     * @return Número pseudoaleatorio en el rango (1, TOTAL_PASAJEROS).
     */
    fun generadorCongruenciaLineal(semilla: Int, lista: IntArray): Int {
        val a = 92 + lista[TOTAL_PASAJEROS / 4]
        val c = 164 + lista[TOTAL_PASAJEROS / 6]
        var x = semilla * lista[TOTAL_PASAJEROS / 3]
        x = (a * x + c) % (TOTAL_PASAJEROS + 1)
        x = (a * x + c) % (TOTAL_PASAJEROS + 1)
        if (x == 0) x = 1
        return x
    }

    /**
     * Método público que permite generar el número pseudoaleatorio.
     *
     * @return Valor generado tras aplicar todos los algoritmos internos.
     */
    fun generar(): Int {
        return calcularPseudoaleatorio()
    }
}
