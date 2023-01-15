/**
 * @file TipoCarta.java
 * @author 202030799 Manuel Rojas
 * @brief Clase para representar los tipos de carta en la app
 * @version 0.1
 * @date 2022-05-08
 *
 *
 */
package com.mrojas.cartas.models;

/**
 * Enum creado para definir los tipos de cartas en el juego
 */

public enum TipoCarta {
    /**
     * Tipo de carta trebol, la cual tiene un acarreo de 0
     */
    TREBOL('♣',0),
    /**
     * Tipo de carta diamante, la cual tiene un acarreo de 20
     */
    DIAMANTE('♦', 20),
    /**
     * Tipo de carta corazón, la cual tiene un acarreo de 40
     */
    CORAZON('♥', 40),
    /**
     * Tipo de carta pica, la cual tiene un acarreo de 60
     */
    PICA('♠',60);
    

    /**
     * Valor que tiene el carreo del tipo de carta a crear
     */
    private int acarreo;
    /**
     * El simbolo con el que se representa cada tipo de carta
     */
    private char simbolo;
    /**
     * Constructor de la clase TipoCarta
     * @param simbolo
     * @param acarreo
     */
    private TipoCarta(char simbolo, int acarreo){
        this.simbolo = simbolo;
        this.acarreo = acarreo;
    }
    /**
     * Metodo para obtener el acarreo de la carta en específico.
     * @return el acarreo del tipo de carta
     */
    public int getAcarreo() {
        return acarreo;
    }

    @Override
    public String toString() {
        return Character.toString(simbolo);
    }   
}
