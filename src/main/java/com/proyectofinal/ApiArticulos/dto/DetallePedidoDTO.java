package com.proyectofinal.ApiArticulos.dto;
//Para recibir un artículo y su cantidad en la compra.


import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotNull;

public class DetallePedidoDTO {
    
    @NotNull(message = "El id del artículo es obligatorio")
    private Long articuloId;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser un número positivo")
    private Integer cantidad;

    // Getters y Setters
    public Long getArticuloId() { return articuloId; }
    public void setArticuloId(Long articuloId) { this.articuloId = articuloId; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
}