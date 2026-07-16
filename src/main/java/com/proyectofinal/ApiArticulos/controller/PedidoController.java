package com.proyectofinal.ApiArticulos.controller;

import com.proyectofinal.ApiArticulos.dto.PedidoRequestDTO;
import com.proyectofinal.ApiArticulos.dto.PedidoResponseDTO;
import com.proyectofinal.ApiArticulos.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // Crear un nuevo pedido usando el usuario del JWT
    @PostMapping
    public ResponseEntity<PedidoResponseDTO> crearPedido(
            @Valid @RequestBody PedidoRequestDTO pedidoDTO,
            @AuthenticationPrincipal UserDetails userDetails) {
        
        String username = userDetails.getUsername();
        PedidoResponseDTO nuevoPedido = pedidoService.crearPedido(pedidoDTO, username);
        return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
    }

    // Obtener el historial de pedidos del usuario autenticado
    @GetMapping("/mis-pedidos")
    public ResponseEntity<List<PedidoResponseDTO>> obtenerMisPedidos(
            @AuthenticationPrincipal UserDetails userDetails) {
        
        String username = userDetails.getUsername();
        List<PedidoResponseDTO> pedidos = pedidoService.obtenerPedidosPorUsuario(username);
        return ResponseEntity.ok(pedidos);
    }

    // Obtener todos los pedidos (Idealmente protegido para que solo acceda ADMIN)
    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(pedidoService.obtenerTodosLosPedidos());
    }
}