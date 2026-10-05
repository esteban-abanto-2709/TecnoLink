package com.tecnolink.tecnolink.proveedores.domain.aggregates;

import java.time.LocalDate;

public class Resena {
    private final Long idResena;
    private final String clienteId;
    private final String publicacionId;
    private final int calificacion;
    private final String comentario;
    private final LocalDate fecha;

    public Resena(Long idResena, String clienteId, String publicacionId, int calificacion, String comentario,
                  LocalDate fecha) {
        if (idResena == null || idResena < 1) {
            throw new IllegalArgumentException("Review id must be positive");
        }
        if (clienteId == null || clienteId.isBlank()) {
            throw new IllegalArgumentException("Review client is required");
        }
        if (publicacionId == null || publicacionId.isBlank()) {
            throw new IllegalArgumentException("Review listing is required");
        }
        if (calificacion < 1 || calificacion > 5) {
            throw new IllegalArgumentException("Review rating must be between 1 and 5");
        }
        if (comentario == null || comentario.length() < 10) {
            throw new IllegalArgumentException("Review comment must have at least 10 characters");
        }
        if (fecha == null) {
            throw new IllegalArgumentException("Review date is required");
        }
        this.idResena = idResena;
        this.clienteId = clienteId;
        this.publicacionId = publicacionId;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    public Long getIdResena() {
        return idResena;
    }

    public String getClienteId() {
        return clienteId;
    }

    public String getPublicacionId() {
        return publicacionId;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
