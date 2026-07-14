package com.proyectofinal.ApiArticulos.controller;

// Importamos el modelo de datos Categoria
import com.proyectofinal.ApiArticulos.dto.CategoriaDTO;
import com.proyectofinal.ApiArticulos.dto.CategoriaResponseDTO;
import com.proyectofinal.ApiArticulos.model.Categoria;
// Importamos el servicio que maneja la lógica de negocio.
import com.proyectofinal.ApiArticulos.service.CategoriaService;

//
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

// Anotaciones de Spring Boot para manejar solicitudes HTTP y permitir CORS
@CrossOrigin(origins = "http://localhost:3306") // Permite solicitudes desde cualquier origen (CORS). 
// Si se quiere restringir, se puede cambiar el valor de origins a un dominio específico, 
// por ejemplo: "http://localhost:3000"
@RestController // Expone métodos como API REST
@RequestMapping("/api/categorias") // Ruta base

public class CategoriaController {

    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<CategoriaResponseDTO> listar() {
        System.out.println("Listando categorías...");
        var categorias = categoriaService.listarCategorias();
        System.out.println("Número de categorías encontradas: " + categorias.size());

        return categorias.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> obtenerPorId(@PathVariable Long id) {
        System.out.println("Obteniendo categoría por ID: " + id);
        return categoriaService.obtenerCategoriaPorId(id)
                .map(this::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> crear(@RequestBody CategoriaDTO categoriaDTO) {
        if (categoriaDTO.getNombre() == null || categoriaDTO.getNombre().isEmpty()) {
            System.out.println("El nombre de la categoría no puede estar vacío.");
            return ResponseEntity.badRequest().build();
        }

        Categoria categoria = new Categoria(categoriaDTO.getNombre(), categoriaDTO.getDescripcion());
        System.out.println("Creando nueva categoría: " + categoria.getNombre());

        return ResponseEntity.ok(toResponseDTO(categoriaService.guardarCategoria(categoria)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> actualizar(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO) {
        System.out.println("Actualizando categoría con ID: " + id);
        System.out.println("Nueva información de la categoría: " + categoriaDTO.getNombre());

        if (categoriaService.obtenerCategoriaPorId(id).isEmpty()) {
            System.out.println("Categoría no encontrada con ID: " + id);
            return ResponseEntity.notFound().build();
        }

        Categoria categoria = new Categoria(categoriaDTO.getNombre(), categoriaDTO.getDescripcion());
        categoria.setId(id);

        return ResponseEntity.ok(toResponseDTO(categoriaService.actualizarCategoria(id, categoria)));
    }

    private CategoriaResponseDTO toResponseDTO(Categoria categoria) {
        return new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getDescripcion()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        System.out.println("Eliminando categoría con ID: " + id);

        if (categoriaService.obtenerCategoriaPorId(id).isEmpty()) {
            System.out.println("Categoría no encontrada con ID: " + id);

            return ResponseEntity.notFound().build();
        }
        categoriaService.eliminarCategoria(id);
        System.out.println("Categoría eliminada con éxito con ID: " + id);

        return ResponseEntity.noContent().build();
    }

}
