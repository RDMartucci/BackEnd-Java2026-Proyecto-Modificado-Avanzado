package com.proyectofinal.ApiArticulos.exception;

public class CategoriaNoEncontradaException extends RuntimeException {

    public CategoriaNoEncontradaException(Long id) {
        super("No existe una categoría con ID " + id);
    }
}