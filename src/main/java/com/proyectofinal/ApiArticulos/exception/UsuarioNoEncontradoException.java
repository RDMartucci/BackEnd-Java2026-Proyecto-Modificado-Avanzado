package com.proyectofinal.ApiArticulos.exception;

public class UsuarioNoEncontradoException extends RuntimeException {

    public UsuarioNoEncontradoException(String email) {
        super("No existe un usuario con email: " + email);
    }
}