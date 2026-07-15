package com.proyectofinal.ApiArticulos.service;

import com.proyectofinal.ApiArticulos.dto.PedidoRequestDTO;
import com.proyectofinal.ApiArticulos.dto.PedidoResponseDTO;
import java.util.List;

public interface PedidoService {
    PedidoResponseDTO crearPedido(PedidoRequestDTO pedidoDTO, String username);
    List<PedidoResponseDTO> obtenerPedidosPorUsuario(String username);
    List<PedidoResponseDTO> obtenerTodosLosPedidos(); // Para Admin
}