/**
 * @file Carta.java
 * @author 202030799 Manuel Rojas
 * @brief Clase para modelar una Carta en la app
 * @version 0.1
 * @date 2022-05-08
 *
 *
 */
package com.mrojas.cartas.models;

/**
 * Clase creada con el fin de poder mapear una carta
 */
public class Carta {
    private String nombre;
    private int valor;
    private TipoCarta tipo;
    private int total;

    /**
     * Constructor de la clase Carta
     * @param nombre La representación en texto del valor de la carta.
     * @param tipo El tipo de carta, puede ser de corazones, pica, trebol o diamante
     * @param valor El valor que representa esa carta del 1 al 13
     */
    public Carta(String nombre, TipoCarta tipo, int valor){
        this.nombre = nombre;
        this.tipo = tipo;
        this.valor = valor;
        total = valor +tipo.getAcarreo();
    }

    /**
     * Metodo para obtener el total de la carta, sumando el acarreo de su tipo.
     * @return el total de la carta
     */
    public int getTotal(){
        return total;
    }

    /**
     * Metodo para obtener la representación en texto del valor de la carta
     * @return la representación en texto de la carta
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Método para obtener el valor que representa la carta por sí misma
     * @return el valor de la carta
     */
    public int getValor(){
        return valor;
    }

    @Override
    public String toString() {
        return nombre + tipo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
        result = prime * result + total;
        result = prime * result + valor;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Carta other = (Carta) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (tipo != other.tipo)
            return false;
        if (total != other.total)
            return false;
        if (valor != other.valor)
            return false;
        return true;
    }
}
