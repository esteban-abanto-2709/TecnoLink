package com.tecnolink.tecnolink.cotizaciones.domain.aggregates;

import com.tecnolink.tecnolink.cotizaciones.domain.entities.Especificacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Comparacion {
    public static final int MINIMO_PRODUCTOS = 2;
    public static final int MAXIMO_PRODUCTOS = 4;

    private final Long idComparacion;
    private final String clienteId;
    private final LocalDate fecha;
    private final List<String> productoIds;

    public Comparacion(Long idComparacion, String clienteId, LocalDate fecha) {
        this(idComparacion, clienteId, fecha, List.of());
    }

    public Comparacion(Long idComparacion, String clienteId, LocalDate fecha, List<String> productoIds) {
        if (idComparacion == null || idComparacion < 1) {
            throw new IllegalArgumentException("Comparison id must be positive");
        }
        if (clienteId == null || clienteId.isBlank()) {
            throw new IllegalArgumentException("Comparison client is required");
        }
        if (fecha == null) {
            throw new IllegalArgumentException("Comparison date is required");
        }
        this.idComparacion = idComparacion;
        this.clienteId = clienteId;
        this.fecha = fecha;
        this.productoIds = new ArrayList<>();
        if (productoIds != null) productoIds.forEach(this::agregarProducto);
    }

    public void agregarProducto(String productoId) {
        if (productoId == null || productoId.isBlank()) {
            throw new IllegalArgumentException("Product id is required");
        }
        if (productoIds.contains(productoId)) {
            throw new IllegalArgumentException("Product " + productoId + " is already in the comparison");
        }
        if (productoIds.size() == MAXIMO_PRODUCTOS) {
            throw new IllegalArgumentException("A comparison allows up to " + MAXIMO_PRODUCTOS + " products");
        }
        productoIds.add(productoId);
    }

    public void quitarProducto(String productoId) {
        if (!productoIds.remove(productoId)) {
            throw new IllegalArgumentException("Product " + productoId + " is not in the comparison");
        }
    }

    public Map<String, Map<String, String>> generarCuadro(List<Especificacion> especificaciones) {
        if (productoIds.size() < MINIMO_PRODUCTOS) {
            throw new IllegalArgumentException("A comparison needs at least " + MINIMO_PRODUCTOS + " products");
        }
        Map<String, Map<String, String>> cuadro = new LinkedHashMap<>();
        for (Especificacion especificacion : especificaciones) {
            if (!productoIds.contains(especificacion.getProductoId())) continue;
            Map<String, String> fila = cuadro.computeIfAbsent(especificacion.getNombre(), nombre -> {
                Map<String, String> vacia = new LinkedHashMap<>();
                productoIds.forEach(id -> vacia.put(id, "-"));
                return vacia;
            });
            fila.put(especificacion.getProductoId(), especificacion.texto());
        }
        return cuadro;
    }

    public Long getIdComparacion() {
        return idComparacion;
    }

    public String getClienteId() {
        return clienteId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public List<String> getProductoIds() {
        return Collections.unmodifiableList(productoIds);
    }
}
