package com.proyectofinal.ApiArticulos.service;

// Importamos el modelo de datos Articulo
import com.proyectofinal.ApiArticulos.model.Articulo;
// Importamos la interfaz del repositorio para acceder a la base de datos
import com.proyectofinal.ApiArticulos.repository.ArticuloRepository;

// Importamos las clases necesarias para manejar listas y opcionales
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Marca la clase como servicio de Spring
public class ArticuloServiceImplemets implements ArticuloService {

    private final ArticuloRepository articuloRepository;

    @Autowired
    public ArticuloServiceImplemets(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    public List<Articulo> listarArticulos() {
        return articuloRepository.findAll();
    }

    public Optional<Articulo> obtenerArticuloPorId(Long id) {
        return articuloRepository.findById(id);
    }

    public Articulo guardarArticulo(Articulo articulo) {
        if (articulo.getNombre() == null || articulo.getNombre().isEmpty()) {
            System.out.println("El nombre del artículo no puede estar vacío.");
            throw new IllegalArgumentException("El nombre del artículo no puede estar vacío.");
        }
        if (articulo.getCategoria() == null || articulo.getCategoria().getId() == null) {
            System.out.println("El artículo debe tener una categoría válida.");
            throw new IllegalArgumentException("El artículo debe tener una categoría válida.");
        }

        return articuloRepository.save(articulo);
    }

    public Articulo actualizarArticulo(Long id, Articulo articulo) {
        articulo.setId(id);
        return articuloRepository.save(articulo);
    }

    public void eliminarArticulo(Long id) {
        articuloRepository.deleteById(id);
    }
}