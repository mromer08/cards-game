/**
 * @file ErrorHandlerController.java
 * @author 202030799 Manuel Rojas
 * @brief Controlador de errores de la API
 * @version 0.1
 * @date 2022-05-08
 *
 *
 */
package com.mrojas.cartas.controllers;

import java.util.Date;

import com.mrojas.cartas.errors.AdditionCardException;
import com.mrojas.cartas.errors.CardNotFoundException;
import com.mrojas.cartas.errors.DuplicateCardException;
import com.mrojas.cartas.errors.ParentNodeException;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Clase controladora de errores de la API
 */
@RestControllerAdvice
public class ErrorHandlerController {

    /**
     * Método el cual es invocado cuando ocurre un error de carta no encontrada
     * @param ex El error encontrado
     * @param model El body del request
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CardNotFoundException.class)
    public void cardNoFoundError(CardNotFoundException ex, Model model){
        model.addAttribute("error", "Error al eliminar carta");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("timestamp", new Date());
        //return "error/add";
    }

    /**
     * Método el cual es invocado cuando ocurre un error de eliminación de cartas por suma
     * @param ex El error encontrado
     * @param model El body del request
     */
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(AdditionCardException.class)
    public void additionCardException(AdditionCardException ex, Model model){
        model.addAttribute("error", "Error al insertar cartas");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("status", HttpStatus.NOT_ACCEPTABLE.value());
        model.addAttribute("timestamp", new Date());
        //return "error/delete";
    }

    /**
     * Método el cual es invocado cuando ocurre un error de eliminación de cartas por ser nodo padre
     * @param ex El error encontrado
     * @param model El body del request
     */
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ParentNodeException.class)
    public void parentNodeError(ParentNodeException ex, Model model){
        model.addAttribute("error", "Error al eliminar carta");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("status", HttpStatus.CONFLICT.value());
        model.addAttribute("timestamp", new Date());
        //return "error/delete";
    }

    /**
     * Método el cual es invocado cuando ocurre un error de inserción de cartas por ser carta repetida
     * @param ex El error encontrado
     * @param model El body del request
     */
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(DuplicateCardException.class)
    public void duplicateNoteError(DuplicateCardException ex, Model model){
        model.addAttribute("error", "Error al insertar carta");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("status", HttpStatus.NOT_ACCEPTABLE.value());
        model.addAttribute("timestamp", new Date());
        //return "error/add";
    }

    /**
     * Método el cual es invocado cuando ocurre un error de ejecución no definido
     * @param ex El error encontrado
     * @param model El body del request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public void genericError(Exception ex, Model model){
        model.addAttribute("error", "Error no definido");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("status", HttpStatus.BAD_REQUEST.value());
        model.addAttribute("timestamp", new Date());
        //return "error/generic";
    }
}