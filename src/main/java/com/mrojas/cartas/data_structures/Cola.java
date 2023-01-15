/**
 * @file Cola.java
 * @author 202030799 Manuel Rojas
 * @brief Clase de estructa de datos tipo Cola
 * @version 0.1
 * @date 2022-05-08
 *
 *
 */
package com.mrojas.cartas.data_structures;

import com.mrojas.cartas.models.Carta;

/**
 * Clase creada para manejar cartas en forma de cola
 */

public class Cola {
    private Nodo inicio, finish;
    private int size;

    /**
     * Constructor para una nueva Cola de Carta
     */
    public Cola(){
        inicio = null;
        size = 0;
    }

    /**
     * Método para insertar un elemento en la cola
     * @param dato La carta a ingresar
     */
    public void insertar(Carta dato){
        if(inicio==null){
            finish= new Nodo(null, null, dato);
            inicio = finish;
        }else{
            Nodo nuevo = new Nodo(null, finish, dato);
            finish.siguiente = nuevo;
            finish=nuevo;
        }
        size++;
    }
    /**
     * Método para obtener un elemento de la cola (el primero)
     * @return Retorna el primer elemento en la cola
     */
    public Carta sacar(){
        Carta dato= inicio.dato;
        inicio=inicio.siguiente;
        if (inicio!=null) {
            inicio.anterior = null;
        } else {
            finish=null;
        }
        size--;
        return dato;
    }

    /**
     * Método para obtener el tamaño de la cola
     * @return El tamaño que tiene la cola
     */
    public int size(){
        return size;
    }

    /**
     * Método para obtener su versión en un arreglo primitivo de cartas
     * @return Un arreglo con las cartas de la cola
     */
    public Carta[] toArray(){
        Carta[] cartas = new Carta[size];
        for (int i = 0; size > 0; i++) {
            cartas[i] = sacar();
        }
        return cartas;
    }
}

/**
 * Clase Nodo de la Cola
 */
class Nodo{
    Carta dato;
    Nodo siguiente, anterior;

    /**
     * Constructor de la clase Nodo para la Cola
     * @param siguiente el siguiente nodo
     * @param anterior el nodo anterior
     * @param dato el dato a almacenar en el nodo
     */
    Nodo(Nodo siguiente, Nodo anterior, Carta dato){
        this.dato = dato;
        this.siguiente = siguiente;
        this.anterior = anterior;
    }
}
