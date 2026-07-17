package com.proyectofinal.ApiArticulos.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
     * ============================================================
     * VALIDACIONES (@Valid)
     * ============================================================
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> manejarValidaciones(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        Map<String, String> errores = new LinkedHashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errores.put(error.getField(), error.getDefaultMessage());
        }

        ErrorResponse response = new ErrorResponse();

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        response.setMessage("La solicitud contiene errores de validación.");
        response.setPath(request.getRequestURI());
        response.setErrors(errores);

        return ResponseEntity.badRequest().body(response);
    }

    /*
     * ============================================================
     * ARTICULOS
     * ============================================================
     */
    @ExceptionHandler(ArticuloNoEncontradoException.class)
    public ResponseEntity<ErrorResponse> manejarArticuloNoEncontrado(
            ArticuloNoEncontradoException ex,
            HttpServletRequest request) {

        return construirError(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                request.getRequestURI());
    }

    /*
     * ============================================================
     * CATEGORIAS
     * ============================================================
     */
    @ExceptionHandler(CategoriaNoEncontradaException.class)
    public ResponseEntity<ErrorResponse> manejarCategoriaNoEncontrada(
            CategoriaNoEncontradaException ex,
            HttpServletRequest request) {

        return construirError(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                request.getRequestURI());
    }

    /*
     * ============================================================
     * USUARIOS
     * ============================================================
     */
    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<ErrorResponse> manejarUsuarioNoEncontrado(
            UsuarioNoEncontradoException ex,
            HttpServletRequest request) {

        return construirError(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                request.getRequestURI());
    }

    /*
     * ============================================================
     * PEDIDOS
     * ============================================================
     */
    @ExceptionHandler(PedidoNoEncontradoException.class)
    public ResponseEntity<ErrorResponse> manejarPedidoNoEncontrado(
            PedidoNoEncontradoException ex,
            HttpServletRequest request) {

        return construirError(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                request.getRequestURI());
    }

    /*
     * ============================================================
     * ARGUMENTOS INVALIDOS
     * ============================================================
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> manejarIllegalArgument(
            IllegalArgumentException ex,
            HttpServletRequest request) {

        return construirError(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                request.getRequestURI());
    }

    /*
     * ============================================================
     * LOGIN
     * ============================================================
     */
    @ExceptionHandler(AuthenticationServiceException.class)
    public ResponseEntity<ErrorResponse> manejarLogin(
            AuthenticationServiceException ex,
            HttpServletRequest request) {

        return construirError(
                HttpStatus.UNAUTHORIZED,
                ex.getMessage(),
                request.getRequestURI());
    }

    /*
     * ============================================================
     * ERRORES GENERALES
     * ============================================================
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> manejarGeneral(
            Exception ex,
            HttpServletRequest request) {

        ex.printStackTrace();

        return construirError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Ha ocurrido un error interno.",
                request.getRequestURI());
    }

    /*
     * ============================================================
     * METODO AUXILIAR
     * ============================================================
     */
    private ResponseEntity<ErrorResponse> construirError(
            HttpStatus status,
            String mensaje,
            String path) {

        ErrorResponse response = new ErrorResponse();

        response.setStatus(status.value());
        response.setError(status.getReasonPhrase());
        response.setMessage(mensaje);
        response.setPath(path);

        return ResponseEntity.status(status).body(response);
    }

}