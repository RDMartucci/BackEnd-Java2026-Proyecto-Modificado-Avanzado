package com.proyectofinal.ApiArticulos.controller;

// Importamos el modelo de datos Articulo
import com.proyectofinal.ApiArticulos.dto.ArticuloDTO;
import com.proyectofinal.ApiArticulos.dto.ArticuloResponseDTO;
import com.proyectofinal.ApiArticulos.model.Articulo;
// Importamos el servicio que maneja la lógica de negocio.
import com.proyectofinal.ApiArticulos.service.ArticuloService;
import com.proyectofinal.ApiArticulos.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*") // Permite solicitudes desde cualquier origen (CORS). 
// Si se quiere restringir, se puede cambiar el valor de origins a un dominio específico, 
// por ejemplo: "http://localhost:3000"
@RestController // Expone métodos como API REST
@RequestMapping("/api/articulos") // Ruta base
public class ArticuloController {

    private final ArticuloService articuloService;
    private final CategoriaService categoriaService;

    @Autowired
    public ArticuloController(ArticuloService articuloService, CategoriaService categoriaService) {
        this.articuloService = articuloService;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<ArticuloResponseDTO> listar() {
        var articulos = articuloService.listarArticulos();
        
        return articulos.stream()
            .map(this::toResponseDTO)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticuloResponseDTO> obtenerPorId(@PathVariable Long id) {
        return articuloService.obtenerArticuloPorId(id)
            .map(this::toResponseDTO)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ArticuloResponseDTO> crear(@RequestBody ArticuloDTO articuloDTO) {
        if (articuloDTO.getNombre() == null || articuloDTO.getNombre().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        if (articuloDTO.getCategoriaId() == null) {
            return ResponseEntity.badRequest().build();
        }

        var categoriaOpt = categoriaService.obtenerCategoriaPorId(articuloDTO.getCategoriaId());
        if (categoriaOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Articulo articulo = new Articulo(
                articuloDTO.getNombre(),
                articuloDTO.getDescripcion(),
                articuloDTO.getEnStock(),
                articuloDTO.getPrecio(),
                categoriaOpt.get()
        );

        Articulo creado = articuloService.guardarArticulo(articulo);
        return ResponseEntity.ok(toResponseDTO(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticuloResponseDTO> actualizar(@PathVariable Long id, @RequestBody ArticuloDTO articuloDTO) {
        if (articuloService.obtenerArticuloPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (articuloDTO.getNombre() == null || articuloDTO.getNombre().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        if (articuloDTO.getCategoriaId() == null) {
            return ResponseEntity.badRequest().build();
        }

        var categoriaOpt = categoriaService.obtenerCategoriaPorId(articuloDTO.getCategoriaId());
        if (categoriaOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Articulo articulo = new Articulo(
                articuloDTO.getNombre(),
                articuloDTO.getDescripcion(),
                articuloDTO.getEnStock(),
                articuloDTO.getPrecio(),
                categoriaOpt.get()
        );
        articulo.setId(id);

        Articulo actualizado = articuloService.actualizarArticulo(id, articulo);
        return ResponseEntity.ok(toResponseDTO(actualizado));
    }

    private ArticuloResponseDTO toResponseDTO(Articulo articulo) {
        return new ArticuloResponseDTO(
                articulo.getId(),
                articulo.getNombre(),
                articulo.getPrecio(),
                articulo.getCategoria() != null ? articulo.getCategoria().getId() : null,
                articulo.getCategoria() != null ? articulo.getCategoria().getNombre() : null,
                articulo.getDescripcion(),
                articulo.getEnStock()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (articuloService.obtenerArticuloPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        articuloService.eliminarArticulo(id);
        return ResponseEntity.noContent().build();
    }
}
