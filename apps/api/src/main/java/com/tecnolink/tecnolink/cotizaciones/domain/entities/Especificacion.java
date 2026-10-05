package com.tecnolink.tecnolink.cotizaciones.domain.entities;

public class Especificacion {
    private final String productoId;
    private final String nombre;
    private final String valor;
    private final String unidad;

    public Especificacion(String productoId, String nombre, String valor, String unidad) {
        if (productoId == null || productoId.isBlank()) {
            throw new IllegalArgumentException("Spec product is required");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Spec name is required");
        }
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("Spec value is required");
        }
        this.productoId = productoId;
        this.nombre = nombre;
        this.valor = valor;
        this.unidad = unidad == null ? "" : unidad;
    }

    public String texto() {
        return unidad.isBlank() ? valor : valor + " " + unidad;
    }

    public String getProductoId() {
        return productoId;
    }

    public String getNombre() {
        return nombre;
    }

    public String getValor() {
        return valor;
    }

    public String getUnidad() {
        return unidad;
    }
}
