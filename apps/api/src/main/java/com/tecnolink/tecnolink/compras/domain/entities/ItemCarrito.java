package com.tecnolink.tecnolink.compras.domain.entities;

public class ItemCarrito {
    private final String publicacionId;
    private int cantidad;
    private final double precioUnitario;

    public ItemCarrito(String publicacionId, int cantidad, double precioUnitario) {
        if (publicacionId == null || publicacionId.isBlank()) {
            throw new IllegalArgumentException("Cart item listing is required");
        }
        if (cantidad < 1) {
            throw new IllegalArgumentException("Cart item quantity must be at least 1");
        }
        if (precioUnitario <= 0) {
            throw new IllegalArgumentException("Cart item price must be greater than 0");
        }
        this.publicacionId = publicacionId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public void aumentar(int cantidad) {
        if (cantidad < 1) {
            throw new IllegalArgumentException("Cart item quantity must be at least 1");
        }
        this.cantidad += cantidad;
    }

    public double subtotal() {
        return cantidad * precioUnitario;
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
}
