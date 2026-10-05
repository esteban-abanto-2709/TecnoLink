package com.tecnolink.tecnolink.catalogo.domain.aggregates;

import com.tecnolink.tecnolink.catalogo.domain.valueobjects.EstadoPublicacion;

import java.time.LocalDate;
import java.util.List;

public class Servicio extends Publicacion {
    private final String modalidad;
    private final String duracionEstimada;
    private final String cobertura;

    public Servicio(String idPublicacion, String proveedorId, String categoriaId, String titulo, String descripcion,
                    double precio, List<String> imagenes, LocalDate fechaPublicacion, EstadoPublicacion estado,
                    String modalidad, String duracionEstimada, String cobertura) {
        super(idPublicacion, proveedorId, categoriaId, titulo, descripcion, precio, imagenes, fechaPublicacion, estado);
        if (modalidad == null || modalidad.isBlank()) {
            throw new IllegalArgumentException("Service mode is required");
        }
        if (duracionEstimada == null || duracionEstimada.isBlank()) {
            throw new IllegalArgumentException("Service estimated duration is required");
        }
        if (cobertura == null || cobertura.isBlank()) {
            throw new IllegalArgumentException("Service coverage is required");
        }
        this.modalidad = modalidad;
        this.duracionEstimada = duracionEstimada;
        this.cobertura = cobertura;
    }

    @Override
    protected String detalleEspecifico() {
        return String.format("Modalidad: %s · Duración: %s · Cobertura: %s", modalidad, duracionEstimada, cobertura);
    }

    public String getModalidad() {
        return modalidad;
    }

    public String getDuracionEstimada() {
        return duracionEstimada;
    }

    public String getCobertura() {
        return cobertura;
    }
}
