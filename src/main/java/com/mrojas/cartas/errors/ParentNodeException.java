/**
 * @file ParentNodeException.java
 * @author 202030799 Manuel Rojas
 * @brief Clase para representar excepción de nodo padre
 * @version 0.1
 * @date 2022-05-08
 *
 *
 */
package com.mrojas.cartas.errors;

/**
 * Excepción para cuando la carta a eliminar es un nodo padre
 */
public class ParentNodeException extends RuntimeException{

    /**
     * Constructor de la excepción
     * @param carta La carta que es nodo padre
     */
    public ParentNodeException(String carta){
        super("La carta ".concat(carta).concat(" no se puede eliminar porque es un nodo padre"));
    }
}
