package com.proyectofinal.ApiArticulos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "articulos")
public class Articulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private Integer enStock;
    
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

        
    // Constructor vacío
    public Articulo() {
    }

    // Constructor con parámetros sin descripción
    public Articulo(String nombre, Double precio, Categoria categoria) {
        this(nombre, null, null, precio, categoria);
    }

    // Constructor con parámetros incluyendo descripción y stock
    public Articulo(String nombre, String descripcion, Integer enStock, Double precio, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.enStock = enStock;
        this.precio = precio;
        this.categoria = categoria;
    }

    // Getters y Setters
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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
