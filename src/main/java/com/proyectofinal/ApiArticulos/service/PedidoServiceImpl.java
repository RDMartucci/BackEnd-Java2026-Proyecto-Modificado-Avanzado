package com.proyectofinal.ApiArticulos.service;

import com.proyectofinal.ApiArticulos.dto.DetallePedidoDTO;
import com.proyectofinal.ApiArticulos.dto.PedidoRequestDTO;
import com.proyectofinal.ApiArticulos.dto.PedidoResponseDTO;
import com.proyectofinal.ApiArticulos.model.*;
import com.proyectofinal.ApiArticulos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ArticuloRepository articuloRepository;

    @Override
    @Transactional
    public PedidoResponseDTO crearPedido(PedidoRequestDTO pedidoDTO, String username) {
        Usuario usuario = usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);

        double total = 0.0;

        for (DetallePedidoDTO item : pedidoDTO.getItems()) {
            Articulo articulo = articuloRepository.findById(item.getArticuloId())
                    .orElseThrow(() -> new RuntimeException("Artículo con ID " + item.getArticuloId() + " no encontrado"));

            // Si manejas stock en tu entidad Articulo, descomenta y adapta esto:
            /*
            if (articulo.getStock() < item.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para: " + articulo.getNombre());
            }
            articulo.setStock(articulo.getStock() - item.getCantidad());
            articuloRepository.save(articulo);
            */

            // El precio de un artículo puede cambiar, guardamos el precio unitario actual
            double precioUnitario = articulo.getPrecio(); // Asumiendo que getPrecio() existe en Articulo
            total += precioUnitario * item.getCantidad();

            DetallePedido detalle = new DetallePedido(articulo, item.getCantidad(), precioUnitario);
            pedido.agregarDetalle(detalle);
        }

        pedido.setTotal(total);
        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        return mapearAPedidoResponse(pedidoGuardado);
    }

    @Override
    public List<PedidoResponseDTO> obtenerPedidosPorUsuario(String username) {
        Usuario usuario = usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        return pedidoRepository.findByUsuario(usuario).stream()
                .map(this::mapearAPedidoResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PedidoResponseDTO> obtenerTodosLosPedidos() {
        return pedidoRepository.findAll().stream()
                .map(this::mapearAPedidoResponse)
                .collect(Collectors.toList());
    }

    // Helper para transformar entidad a DTO de respuesta
    private PedidoResponseDTO mapearAPedidoResponse(Pedido pedido) {
        PedidoResponseDTO response = new PedidoResponseDTO();
        response.setId(pedido.getId());
        //response.setUsernameCliente(pedido.getUsuario().getUsername());
        response.setUsernameCliente(pedido.getUsuario().getNombre());
        response.setFechaPedido(pedido.getFechaPedido());
        response.setTotal(pedido.getTotal());
        response.setEstado(pedido.getEstado());

        List<PedidoResponseDTO.DetalleResponseDTO> detallesDTO = pedido.getDetalles().stream()
                .map(det -> new PedidoResponseDTO.DetalleResponseDTO(
                        det.getArticulo().getNombre(), // Asumiendo getNombre() en tu clase Articulo
                        det.getCantidad(),
                        det.getPrecioUnitario()
                ))
                .collect(Collectors.toList());

        response.setDetalles(detallesDTO);
        return response;
    }
}