/**
 * @file AdditionCardException.java
 * @author 202030799 Manuel Rojas
 * @brief Clase para representar excepción de suma de cartas
 * @version 0.1
 * @date 2022-05-08
 *
 *
 */
package com.mrojas.cartas.errors;
/**
 * Excepción específica para cuando se presenta un error de sumatoria al eliminar cartas
 */

public class AdditionCardException extends RuntimeException {
    /**
     * Constructor para la excepción cuando se quieren eliminar dos cartas
     * @param carta1 la primera carta
     * @param carta2 la segunda carta
     */
    public AdditionCardException(String carta1, String carta2){
        super("Las cartas ".concat(carta1).concat(" y ").concat(carta2).concat("no se pueden eliminar porque no suman 13"));
    }
    /**
     * Constructor para la excepción cuando se quieren eliminar solo una carta
     * @param carta1 la primera carta
     */
    public AdditionCardException(String carta1){
        super("La carta ".concat(carta1).concat(" no tiene el valor suficiente para eliminarse sola"));
    }
}
