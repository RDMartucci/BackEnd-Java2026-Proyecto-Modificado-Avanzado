package com.proyectofinal.ApiArticulos.exception;

public class PedidoNoEncontradoException extends RuntimeException {

    public PedidoNoEncontradoException(Long id) {
        super("No existe un artículo con ID " + id);
    }
}