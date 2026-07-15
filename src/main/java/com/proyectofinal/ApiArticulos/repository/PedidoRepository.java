package com.proyectofinal.ApiArticulos.repository;

import com.proyectofinal.ApiArticulos.model.Pedido;
import com.proyectofinal.ApiArticulos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // Buscar los pedidos de un usuario específico
    List<Pedido> findByUsuario(Usuario usuario);
}