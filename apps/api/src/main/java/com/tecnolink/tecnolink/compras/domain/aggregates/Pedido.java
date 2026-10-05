package com.tecnolink.tecnolink.compras.domain.aggregates;

import com.tecnolink.tecnolink.compras.domain.entities.DetallePedido;
import com.tecnolink.tecnolink.compras.domain.entities.ItemCarrito;
import com.tecnolink.tecnolink.compras.domain.valueobjects.EstadoPedido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {
    public static final int SOLES_POR_PUNTO = 10;

    private final Long idPedido;
    private final String clienteId;
    private final LocalDate fecha;
    private final List<DetallePedido> detalles;
    private final double total;
    private EstadoPedido estado;
    private final String metodoPagoSimulado;

    public Pedido(Long idPedido, String clienteId, LocalDate fecha, List<DetallePedido> detalles,
                  EstadoPedido estado, String metodoPagoSimulado) {
        if (idPedido == null || idPedido < 1) {
            throw new IllegalArgumentException("Order id must be positive");
        }
        if (clienteId == null || clienteId.isBlank()) {
            throw new IllegalArgumentException("Order client is required");
        }
        if (fecha == null) {
            throw new IllegalArgumentException("Order date is required");
        }
        if (detalles == null || detalles.isEmpty()) {
            throw new IllegalArgumentException("An order needs at least one line");
        }
        if (estado == null) {
            throw new IllegalArgumentException("Order status is required");
        }
        if (metodoPagoSimulado == null || metodoPagoSimulado.isBlank()) {
            throw new IllegalArgumentException("Order payment method is required");
        }
        this.idPedido = idPedido;
        this.clienteId = clienteId;
        this.fecha = fecha;
        this.detalles = new ArrayList<>(detalles);
        double suma = 0;
        for (DetallePedido detalle : detalles) suma += detalle.getSubtotal();
        this.total = suma;
        this.estado = estado;
        this.metodoPagoSimulado = metodoPagoSimulado;
    }

    public static Pedido desdeCarrito(Long idPedido, Carrito carrito, LocalDate fecha, String metodoPagoSimulado) {
        if (carrito.estaVacio()) {
            throw new IllegalArgumentException("Cannot order an empty cart");
        }
        List<DetallePedido> detalles = new ArrayList<>();
        for (ItemCarrito item : carrito.getItems()) {
            detalles.add(new DetallePedido(item.getPublicacionId(), item.getCantidad(), item.getPrecioUnitario()));
        }
        return new Pedido(idPedido, carrito.getClienteId(), fecha, detalles, EstadoPedido.PENDIENTE, metodoPagoSimulado);
    }

    public boolean simularPago() {
        if (estado != EstadoPedido.PENDIENTE) return false;
        estado = EstadoPedido.PAGADO;
        return true;
    }

    public void confirmar() {
        if (estado != EstadoPedido.PAGADO) {
            throw new IllegalArgumentException("Only a paid order can be confirmed");
        }
        estado = EstadoPedido.CONFIRMADO;
    }

    public int calcularPuntos() {
        return (int) Math.floor(total / SOLES_POR_PUNTO);
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public String getClienteId() {
        return clienteId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public List<DetallePedido> getDetalles() {
        return Collections.unmodifiableList(detalles);
    }

    public double getTotal() {
        return total;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public String getMetodoPagoSimulado() {
        return metodoPagoSimulado;
    }
}
