package com.proyectofinal.ApiArticulos.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoResponseDTO {
    private Long id;
    private String usernameCliente;
    private LocalDateTime fechaPedido;
    private Double total;
    private String estado;
    private List<DetalleResponseDTO> detalles;

    // Sub-clase para mapear de manera estática los detalles
    public static class DetalleResponseDTO {
        private String articuloNombre;
        private Integer cantidad;
        private Double precioUnitario;

        public DetalleResponseDTO(String articuloNombre, Integer cantidad, Double precioUnitario) {
            this.articuloNombre = articuloNombre;
            this.cantidad = cantidad;
            this.precioUnitario = precioUnitario;
        }
        // Getters
        public String getArticuloNombre() { return articuloNombre; }
        public Integer getCantidad() { return cantidad; }
        public Double getPrecioUnitario() { return precioUnitario; }
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsernameCliente() { return usernameCliente; }
    public void setUsernameCliente(String usernameCliente) { this.usernameCliente = usernameCliente; }

    public LocalDateTime getFechaPedido() { return fechaPedido; }
    public void setFechaPedido(LocalDateTime fechaPedido) { this.fechaPedido = fechaPedido; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public List<DetalleResponseDTO> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleResponseDTO> detalles) { this.detalles = detalles; }
}
