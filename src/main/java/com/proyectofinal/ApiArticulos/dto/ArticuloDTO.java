package com.proyectofinal.ApiArticulos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

// clase ArticuloDTO que representa un objeto de transferencia de datos para un artículo.
public class ArticuloDTO {

@NotBlank(message = "El nombre es obligatorio")
@Size(min = 3, max = 100)
private String nombre;

@NotBlank(message = "La descripción es obligatoria")
@Size(max = 500)
private String descripcion;

@NotNull(message = "Debe indicar un precio")
@Positive(message = "El precio debe ser mayor a cero")
private Double precio;

@NotNull(message = "Debe indicar el stock")
@PositiveOrZero(message = "El stock no puede ser negativo")
private Integer stock;

@NotNull(message = "Debe indicar una categoría")
private Long categoriaId;

    // Constructor vacío necesario para la deserialización de JSON a objeto Java.
    public ArticuloDTO() {
    }

    // Constructor con parámetros para inicializar un objeto ArticuloDTO con nombre, precio y categoría.
    public ArticuloDTO(String nombre, String descripcion, Integer Stock, Double precio, Long categoriaId) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = Stock;
        this.precio = precio;
        this.categoriaId = categoriaId;
    }

    // Getters y setters para los campos de la clase.
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getEnStock() {
        return enStock;
    }

    public void setEnStock(Integer enStock) {
        this.enStock = enStock;
    }
}
