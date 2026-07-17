package com.proyectofinal.ApiArticulos.exception;

public class ArticuloNoEncontradoException extends RuntimeException {

    public ArticuloNoEncontradoException(Long id) {
        super("No existe un artículo con ID " + id);
    }
}