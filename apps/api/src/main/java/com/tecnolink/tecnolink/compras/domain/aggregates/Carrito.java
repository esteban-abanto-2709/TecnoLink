package com.tecnolink.tecnolink.compras.domain.aggregates;

import com.tecnolink.tecnolink.compras.domain.entities.ItemCarrito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Carrito {
    private final Long idCarrito;
    private final String clienteId;
    private final List<ItemCarrito> items = new ArrayList<>();

    public Carrito(Long idCarrito, String clienteId) {
        if (idCarrito == null || idCarrito < 1) {
            throw new IllegalArgumentException("Cart id must be positive");
        }
        if (clienteId == null || clienteId.isBlank()) {
            throw new IllegalArgumentException("Cart client is required");
        }
        this.idCarrito = idCarrito;
        this.clienteId = clienteId;
    }

    public void agregarItem(String publicacionId, int cantidad, double precioUnitario) {
        for (ItemCarrito item : items) {
            if (item.getPublicacionId().equals(publicacionId)) {
                item.aumentar(cantidad);
                return;
            }
        }
        items.add(new ItemCarrito(publicacionId, cantidad, precioUnitario));
    }

    public void eliminarItem(String publicacionId) {
        if (!items.removeIf(item -> item.getPublicacionId().equals(publicacionId))) {
            throw new IllegalArgumentException("Listing " + publicacionId + " is not in the cart");
        }
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemCarrito item : items) total += item.subtotal();
        return total;
    }

    public void vaciar() {
        items.clear();
    }

    public boolean estaVacio() {
        return items.isEmpty();
    }

    public Long getIdCarrito() {
        return idCarrito;
    }

    public String getClienteId() {
        return clienteId;
    }

    public List<ItemCarrito> getItems() {
        return Collections.unmodifiableList(items);
    }
}
