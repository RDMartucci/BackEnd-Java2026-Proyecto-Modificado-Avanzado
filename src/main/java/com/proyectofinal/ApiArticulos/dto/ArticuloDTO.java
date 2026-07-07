package com.proyectofinal.ApiArticulos.dto;

// clase ArticuloDTO que representa un objeto de transferencia de datos para un artículo.
public class ArticuloDTO {

    private String nombre;  
    private String descripcion;
    private Integer enStock;
    private Double precio;  
    private Long categoriaId;

    // Constructor vacío necesario para la deserialización de JSON a objeto Java.
    public ArticuloDTO() {
    }

    // Constructor con parámetros para inicializar un objeto ArticuloDTO con nombre, precio y categoría.
    public ArticuloDTO(String nombre, String descripcion, Integer enStock, Double precio, Long categoriaId) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.enStock = enStock;
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
