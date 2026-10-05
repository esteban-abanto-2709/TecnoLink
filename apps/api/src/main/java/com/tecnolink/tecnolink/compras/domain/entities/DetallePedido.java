package com.tecnolink.tecnolink.compras.domain.entities;

public class DetallePedido {
    private final String publicacionId;
    private final int cantidad;
    private final double precioUnitario;
    private final double subtotal;

    public DetallePedido(String publicacionId, int cantidad, double precioUnitario) {
        if (publicacionId == null || publicacionId.isBlank()) {
            throw new IllegalArgumentException("Order line listing is required");
        }
        if (cantidad < 1) {
            throw new IllegalArgumentException("Order line quantity must be at least 1");
        }
        if (precioUnitario <= 0) {
            throw new IllegalArgumentException("Order line price must be greater than 0");
        }
        this.publicacionId = publicacionId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = cantidad * precioUnitario;
    }

    public String getPublicacionId() {
        return publicacionId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }
}
