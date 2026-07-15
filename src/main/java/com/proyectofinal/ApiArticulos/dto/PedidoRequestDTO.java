package com.proyectofinal.ApiArticulos.dto;
//Para que el cliente envíe su carrito de compras.


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class PedidoRequestDTO {

    @NotEmpty(message = "El pedido debe contener al menos un artículo")
    @Valid
    private List<DetallePedidoDTO> items;

    // Getters y Setters
    public List<DetallePedidoDTO> getItems() { return items; }
    public void setItems(List<DetallePedidoDTO> items) { this.items = items; }
}