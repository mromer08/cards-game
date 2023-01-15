/**
 * @file DuplicateCardException.java
 * @author 202030799 Manuel Rojas
 * @brief Clase para representar excepción de carta duplicada
 * @version 0.1
 * @date 2022-05-08
 *
 *
 */
package com.mrojas.cartas.errors;

/**
 * Excepción creada para cuando la carta a insertar está duplicada
 */
public class DuplicateCardException extends RuntimeException{
    /**
     * Constructor de la excepción
     * @param carta la carta que está duplicada
     */
    public DuplicateCardException(String carta){
        super("La carta ".concat(carta).concat(" se encuentra duplicada"));
    }
}
