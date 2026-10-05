package com.tecnolink.tecnolink.catalogo.domain.aggregates;

import com.tecnolink.tecnolink.catalogo.domain.valueobjects.EstadoPublicacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Publicacion {
    private final String idPublicacion;
    private final String proveedorId;
    private final String categoriaId;
    private String titulo;
    private String descripcion;
    private double precio;
    private final List<String> imagenes;
    private final LocalDate fechaPublicacion;
    private EstadoPublicacion estado;

    protected Publicacion(String idPublicacion, String proveedorId, String categoriaId, String titulo,
                          String descripcion, double precio, List<String> imagenes, LocalDate fechaPublicacion,
                          EstadoPublicacion estado) {
        if (idPublicacion == null || idPublicacion.isBlank()) {
            throw new IllegalArgumentException("Listing id is required");
        }
        if (proveedorId == null || proveedorId.isBlank()) {
            throw new IllegalArgumentException("Listing supplier is required");
        }
        if (categoriaId == null || categoriaId.isBlank()) {
            throw new IllegalArgumentException("Listing category is required");
        }
        if (fechaPublicacion == null) {
            throw new IllegalArgumentException("Listing date is required");
        }
        if (estado == null) {
            throw new IllegalArgumentException("Listing status is required");
        }
        validar(titulo, descripcion, precio);
        this.idPublicacion = idPublicacion;
        this.proveedorId = proveedorId;
        this.categoriaId = categoriaId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagenes = new ArrayList<>(imagenes == null ? List.of() : imagenes);
        this.fechaPublicacion = fechaPublicacion;
        this.estado = estado;
    }

    public void actualizar(String titulo, String descripcion, double precio) {
        validar(titulo, descripcion, precio);
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public void pausar() {
        estado = EstadoPublicacion.PAUSADA;
    }

    public void activar() {
        estado = EstadoPublicacion.ACTIVA;
    }

    public String verDetalle() {
        return String.format("%s%nS/ %.2f%n%s%n%s", titulo, precio, descripcion, detalleEspecifico());
    }

    protected abstract String detalleEspecifico();

    private static void validar(String titulo, String descripcion, double precio) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Listing title is required");
        }
        if (descripcion == null || descripcion.length() < 20) {
            throw new IllegalArgumentException("Listing description must have at least 20 characters");
        }
        if (precio <= 0) {
            throw new IllegalArgumentException("Listing price must be greater than 0");
        }
    }

    public String getIdPublicacion() {
        return idPublicacion;
    }

    public String getProveedorId() {
        return proveedorId;
    }

    public String getCategoriaId() {
        return categoriaId;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public List<String> getImagenes() {
        return Collections.unmodifiableList(imagenes);
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public EstadoPublicacion getEstado() {
        return estado;
    }
}
