package com.proyectofinal.ApiArticulos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categorias")

public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nombre;
    private String descripcion;

    // Constructor vacío
    public Categoria() {
    }

    // Constructor con parámetros
    public Categoria(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    //getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        // Validación para asegurarse de que el ID no sea negativo.
        if (id != null && id < 0) {
            throw new IllegalArgumentException("El ID no puede ser negativo");
        }
        // Asignar el valor del ID si es válido.
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            // Validación para asegurarse de que el nombre no esté vacío.
            // Si el nombre está vacío, se lanza una excepción.
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            // Validación para asegurarse de que la descripción no esté vacía.
            // Si la descripción está vacía, se lanza una excepción.
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        }
        this.descripcion = descripcion;
    }





}
