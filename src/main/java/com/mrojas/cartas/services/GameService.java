package com.mrojas.cartas.services;

import com.mrojas.cartas.data_structures.ArbolAVL;
import com.mrojas.cartas.errors.DuplicateCardException;
import com.mrojas.cartas.models.Carta;
import com.mrojas.cartas.models.TipoCarta;

import org.springframework.stereotype.Service;

/**
 * Clase de servicio para el juego
 */
@Service
public class GameService {
    /**
     * El conjunto de cartas que se estarán manejando durante el juego
     */
    private ArbolAVL baraja;

    /**
     * Método que crea una nueva baraja de cartas e inserta cartas iniciales
     * @param cartas El conjunto de cartas con las que se iniciará el juego
     */
    public void start(Carta[] cartas){
        baraja = new ArbolAVL();
        for (Carta carta : cartas) {
            if (containsCard(carta)) {
                throw new DuplicateCardException(carta.toString());
            }
            baraja.insertar(carta);
        }
    }

    /**
     * Método para insertar una carta en la baraja del juego
     * @param carta La carta a insertar
     */
    public void addCard(Carta carta){
        baraja.insertar(carta);
    }

    /**
     * Método para eliminar una carta en la baraja del juego
     * @param carta La carta a eliminar
     */
    public void deleteCard(Carta carta){
        baraja.eliminar(carta);
    }

    /**
     * Método para gráficar el estado actual del arbol avl
     * @param path La ruta en la cual se guardará la imagen
     */
    public void getStatusTree(String path){
        baraja.graficar(path);
    }

    /**
     * Método para obtener las cartas en cierto nivel del arbol avl
     * @param level el nivel que se quiere obtener
     * @return Las cartas en el nivel indicado
     */
    public Carta[] getLevelTree(int level){
        return baraja.getNivel(level);
    }

    /**
     * Método para obtener los diferentes recorridos del arbol avl
     * @param order El tipo de recorrido que se le pide al arbol
     * @return El recorrido solicitado
     */
    public Carta[] getTransversalTree(String order){
        Carta[] cartas = null;
        switch (order) {
            case "inOrder"-> cartas = baraja.inOrder();
            case "preOrder" -> cartas = baraja.preOrder();
            case "postOrder" -> cartas = baraja.posOrder();
        }
        return cartas;
    }

    /**
     * Método para saber la altura que tiene el arbol actualmente
     * @return El valor de la altura del árbol. Si es null retorna -1
     */
    public int getTreeHeight(){
        return baraja.altura();
    }

    /**
     * Método para saber si la carta se encuentra dentro de la baraja
     * @param carta La carta a buscar
     * @return Retorna true si la carta fue encontrada, de lo contrario es false.
     */
    public boolean containsCard(Carta carta){
        return baraja.buscar(carta) == null ? false : true;
    }

    /**
     * Método para saber si una carta es un nodo padre
     * @param carta La carta que se desea comprobar
     * @return Retorna true si la carta es nodo padre, de lo contrario retorna false
     */
    public boolean isParentCard(Carta carta){
        return baraja.altura(baraja.buscar(carta)) != 0 ? true : false;
    }

    /**
     * Método para crear una nueva carta a raiz de su representación en texto
     * @param texto El texto que representa la carta a crear
     * @return Retorna null si el texto no es válido, de lo contrario retorna una nueva Carta
     */
    public Carta crearCarta(String texto){
        if (texto == null || texto.isBlank()) {
            return null;
        }
        boolean valida = true;
        TipoCarta tipo = null;
        String nombre = "";
        int valor = 0;
        switch (texto.charAt(texto.length()-1)) {
            case '♣' -> tipo = TipoCarta.TREBOL;
            case '♦' -> tipo = TipoCarta.DIAMANTE;
            case '♥' -> tipo = TipoCarta.CORAZON;
            case '♠' -> tipo = TipoCarta.PICA;
            default -> valida = false;
        }

        switch (texto.length()) {
            case 2 -> nombre += Character.toString(texto.charAt(0));
            case 3 -> nombre += Character.toString(texto.charAt(0)) + Character.toString(texto.charAt(1));
            default -> valida = false;
        }

        switch (nombre) {
            case "A"-> valor = 1;
            case "2"-> valor = 2;
            case "3"-> valor = 3;
            case "4"-> valor = 4;
            case "5"-> valor = 5;
            case "6"-> valor = 6;
            case "7"-> valor = 7;
            case "8"-> valor = 8;
            case "9"-> valor = 9;
            case "10"-> valor = 10;
            case "J"-> valor = 11;
            case "Q"-> valor = 12;
            case "K"-> valor = 13;
            default -> valida = false;
        }

        if (!valida) {
            return  null;
        }

        return new Carta(nombre, tipo, valor);
    }
}
