package com.proyectofinal.ApiArticulos.dto;

// clase ArticuloResponseDTO que representa un objeto de transferencia de datos para la respuesta de un artículo.
public class ArticuloResponseDTO {

    private Long id;
    private String nombre;
    private Double precio;
    private Long categoriaId;
    private String categoriaNombre;

    public ArticuloResponseDTO() {
    }

    public ArticuloResponseDTO(Long id, String nombre, Double precio, Long categoriaId, String categoriaNombre) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categoriaId = categoriaId;
        this.categoriaNombre = categoriaNombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }
}
