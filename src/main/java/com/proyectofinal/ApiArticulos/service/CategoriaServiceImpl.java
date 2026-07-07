package com.proyectofinal.ApiArticulos.service;

import com.proyectofinal.ApiArticulos.model.Categoria;
import com.proyectofinal.ApiArticulos.repository.CategoriaRepository;

import java.util.List;
import java.util.Optional;

// Importamos las clases necesarias para manejar listas y opcionales
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }
    @Override
    public List<Categoria> listarCategorias() {
        if (categoriaRepository.count() == 0) {
            System.out.println("No hay categorías en la base de datos.");

            return List.of(); // Retorna una lista vacía si no hay categorías.return List.of(new Categoria(1L, "Sin categoría")); // Retorna una lista con una categoría por defecto si no hay categorías.    
    
        } else {
            System.out.println("Número de categorías en la base de datos: " + categoriaRepository.count());
        }
        return categoriaRepository.findAll();
    }
    @Override
    public Optional<Categoria> obtenerCategoriaPorId(Long id) {
        if (!categoriaRepository.existsById(id)) {
            System.out.println("La categoría con ID " + id + " no existe.");
            
            return Optional.empty();
        }
        System.out.println("Obteniendo categoría con ID: " + id);

        return categoriaRepository.findById(id);
    }
    @Override
    public Categoria guardarCategoria(Categoria categoria) {
        if (categoria.getNombre() == null || categoria.getNombre().isEmpty()) {
            System.out.println("El nombre de la categoría no puede estar vacío.");

            throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío.");  

            //return null; // Retorna null si el nombre de la categoría está vacío.
            
        }
        System.out.println("Guardando categoría: " + categoria.getNombre());

        return categoriaRepository.save(categoria);
    }
    @Override
    public Categoria actualizarCategoria(Long id, Categoria categoria) {
        if (categoria.getNombre() == null || categoria.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío.");
        }
        System.out.println("Actualizando categoría: " + categoria.getNombre());

        categoria.setId(id);
        return categoriaRepository.save(categoria);
    }
    @Override
    public void eliminarCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new IllegalArgumentException("La categoría con ID " + id + " no existe.");
        }
        System.out.println("Eliminando categoría con ID: " + id);

        categoriaRepository.deleteById(id);
    }

}
