package com.tecnolink.tecnolink.fidelizacion.domain.entities;

import com.tecnolink.tecnolink.fidelizacion.domain.valueobjects.TipoMovimiento;

import java.time.LocalDate;

public class MovimientoPuntos {
    private final LocalDate fecha;
    private final TipoMovimiento tipo;
    private final int puntos;
    private final String motivo;

    public MovimientoPuntos(LocalDate fecha, TipoMovimiento tipo, int puntos, String motivo) {
        if (fecha == null) {
            throw new IllegalArgumentException("Movement date is required");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("Movement type is required");
        }
        if (puntos < 1) {
            throw new IllegalArgumentException("Movement points must be greater than 0");
        }
        if (motivo == null || motivo.isBlank()) {
            throw new IllegalArgumentException("Movement reason is required");
        }
        this.fecha = fecha;
        this.tipo = tipo;
        this.puntos = puntos;
        this.motivo = motivo;
    }

    public int efectoEnSaldo() {
        return tipo == TipoMovimiento.ACUMULACION ? puntos : -puntos;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public TipoMovimiento getTipo() {
        return tipo;
    }

    public int getPuntos() {
        return puntos;
    }

    public String getMotivo() {
        return motivo;
    }
}
