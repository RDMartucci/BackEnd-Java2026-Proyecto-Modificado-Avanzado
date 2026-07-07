package com.proyectofinal.ApiArticulos.service;

import java.util.List;
import java.util.Optional;

// Importamos el modelo de datos Categoria
import com.proyectofinal.ApiArticulos.model.Categoria;


public interface CategoriaService {
    List<Categoria> listarCategorias();
    Optional<Categoria> obtenerCategoriaPorId(Long id);
    Categoria guardarCategoria(Categoria categoria);
    Categoria actualizarCategoria(Long id, Categoria categoria);
    void eliminarCategoria(Long id);
}
