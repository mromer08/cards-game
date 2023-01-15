/**
 * @file GameController.java
 * @author 202030799 Manuel Rojas
 * @brief Controlador de rutas de la API
 * @version 0.1
 * @date 2022-05-08
 *
 *
 */
package com.mrojas.cartas.controllers;

import java.util.HashMap;
import java.util.Map;
import com.mrojas.cartas.errors.AdditionCardException;
import com.mrojas.cartas.errors.CardNotFoundException;
import com.mrojas.cartas.errors.DuplicateCardException;
import com.mrojas.cartas.errors.ParentNodeException;
import com.mrojas.cartas.models.Carta;
import com.mrojas.cartas.services.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * Clase controladora de la API, maneja las diferentes rutas de la app
 */
@RestController
@RequestMapping("/Game")
public class GameController {
    
    /**
     * Clase de servicio la cual tiene conexión directa con las estructura de datos
     */
    @Autowired
    GameService service;

    /**
     * Metodo para controlar el inicio de la aplicación
     * @param model body del request con las cartas iniciales para iniciar el juego
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/start")
    public void start(@RequestBody Map<String, Object> model) {
        Carta[] cartas = new Carta[model.size()];
        for (int i = 0; i < model.size(); i++) {
            cartas[i] = service.crearCarta((String) model.get(Integer.toString(i)));
        }
        for (Carta carta : cartas) {
            if (carta == null) {
                throw new NullPointerException("Hay cartas a ingresar que no cumplen con el formato correcto");
            }
        }
        service.start(cartas);
    }

    /**
     * Método para controlar la inserción de cartas en el juego
     * @param model body del request que contiene la carta a insertar en el arbol bajo el parametro 'insert'
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/add")
    public void add(@RequestBody Map<String, Object> model) {
        Carta carta = service.crearCarta((String) model.get("insert"));
        if (service.containsCard(carta)) {
            throw new DuplicateCardException(carta.toString());
        }
        service.addCard(carta);
    }

    /**
     * Método que controla la eliminación de cartas en el juego
     * @param model body del request que contiene las cartas a eliminar en el arbol bajo el parametro 'delete_1' o 'delete_2'
     */
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete")
    public void delete(@RequestBody Map<String, Object> model) {
        Carta carta1 = service.crearCarta((String) model.get("delete_1"));
        Carta carta2 = service.crearCarta((String) model.get("delete_2"));

        if (!service.containsCard(carta1)) {
            throw new CardNotFoundException(carta1.toString());
        }

        if (carta2 == null) {
            if (service.isParentCard(carta1)) {
                throw new ParentNodeException(carta1.toString());
            }

            if (carta1.getValor() != 13) {
                throw new AdditionCardException(carta1.toString());
            }

            service.deleteCard(carta1);
        } else {
            if (!service.containsCard(carta2)) {
                throw new CardNotFoundException(carta2.toString());
            }
            if (service.isParentCard(carta1)) {
                throw new ParentNodeException(carta1.toString());
            }
            if (service.isParentCard(carta2)) {
                throw new ParentNodeException(carta2.toString());
            }
            if ((carta1.getValor() + carta2.getValor()) != 13) {
                throw new AdditionCardException(carta1.toString(), carta2.toString());
            }

            service.deleteCard(carta1);
            service.deleteCard(carta2);
        }
    }

    /**
     * Método que controla la devolución del status del arbol
     * @param host el host bajo el cual se encuentra alojado la app
     * @return Retorna un body en el cual va el path de la imagen con el estatus del arbol
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/status-avltree")
    public Map<String, String> getStatus(@RequestHeader String host) {
        Map<String, String> model = new HashMap<>();
        String path = "src//main//resources//static/images/status.jpg";
        service.getStatusTree(path);
        model.put("path", "http://".concat(host).concat("/images/status.jpg"));
        return model;
    }

    /**
     * Método que controla la devolución de cartas por nivel del arbol
     * @param level El nivel del cual se quieren saber las cartas del arbol, comenzando del 1 en la raiz
     * @return Retorna un body con las cartas en ese nivel de izquierda a derecha, numeradas empezando desde el 0.
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/get-level")
    public Map<Integer, String> getLevel(@RequestParam("level") Integer level) {
        if (level < 1 || (level-1) > service.getTreeHeight() ) {
            throw new ArrayIndexOutOfBoundsException("El nivel del arbol debe ser positivo mayor a 0");
        }
        Map<Integer, String> nivel = new HashMap<>();
        Carta[] cartas = service.getLevelTree(level-1);
        for (int i = 0; i < cartas.length; i++) {
            nivel.put(i, cartas[i].toString());
        }
        return nivel;
    }

    /**
     * Método que controla la devolución de los diferentes recorridos del arbol
     * @param transversal El tipo de recorrido que realizará el arbol
     * @return Retorna el recorrido solicitado en el body
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/avltree")
    public Map<Integer, String> getTransversal(@RequestParam("transversal") String transversal) {
        Map<Integer, String> recorrido = new HashMap<>();
        Carta[] cartas = service.getTransversalTree(transversal);
        for (int i = 0; i < cartas.length; i++) {
            recorrido.put(i, cartas[i].toString());
        }
        return recorrido;
    }
}
