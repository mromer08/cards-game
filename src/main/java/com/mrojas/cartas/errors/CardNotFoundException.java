/**
 * @file CardNotFoundException.java
 * @author 202030799 Manuel Rojas
 * @brief Clase para representar excepción de carta no encontrada
 * @version 0.1
 * @date 2022-05-08
 *
 *
 */
package com.mrojas.cartas.errors;

/**
 * Excepción creada para cuando la carta no se encuentra dentro del árbol
 */
public class CardNotFoundException extends RuntimeException{
    /**
     * Constructor de la excepción
     * @param carta la carta que no fue encontrada en el árbol avl
     */
    public CardNotFoundException(String carta){
        super("La carta ".concat(carta).concat(" no se encuentra en el arbol para poder eliminarla."));
    }
}
