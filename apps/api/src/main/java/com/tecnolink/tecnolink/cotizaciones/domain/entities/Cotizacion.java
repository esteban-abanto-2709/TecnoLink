package com.tecnolink.tecnolink.cotizaciones.domain.entities;

import java.time.LocalDate;

public class Cotizacion {
    public static final double TASA_IGV = 0.18;

    private final Long idCotizacion;
    private final LocalDate fechaEmision;
    private final int diasVigencia;
    private final double subtotal;
    private final double igv;
    private final double total;
    private final String condiciones;

    public Cotizacion(Long idCotizacion, LocalDate fechaEmision, int diasVigencia, double subtotal, String condiciones) {
        if (idCotizacion == null || idCotizacion < 1) {
            throw new IllegalArgumentException("Quote id must be positive");
        }
        if (fechaEmision == null) {
            throw new IllegalArgumentException("Quote issue date is required");
        }
        if (diasVigencia < 1) {
            throw new IllegalArgumentException("Quote validity must be at least 1 day");
        }
        if (subtotal <= 0) {
            throw new IllegalArgumentException("Quote subtotal must be greater than 0");
        }
        if (condiciones == null || condiciones.isBlank()) {
            throw new IllegalArgumentException("Quote conditions are required");
        }
        this.idCotizacion = idCotizacion;
        this.fechaEmision = fechaEmision;
        this.diasVigencia = diasVigencia;
        this.subtotal = redondear(subtotal);
        this.igv = redondear(subtotal * TASA_IGV);
        this.total = redondear(this.subtotal + this.igv);
        this.condiciones = condiciones;
    }

    public LocalDate fechaVencimiento() {
        return fechaEmision.plusDays(diasVigencia);
    }

    public boolean estaVigente(LocalDate hoy) {
        return !hoy.isAfter(fechaVencimiento());
    }

    public String generarFormato() {
        return String.format(
                "Cotización N° %d%nEmitida: %s · Vigente hasta: %s%nSubtotal: S/ %.2f%nIGV (18%%): S/ %.2f%nTotal: S/ %.2f%nCondiciones: %s",
                idCotizacion, fechaEmision, fechaVencimiento(), subtotal, igv, total, condiciones);
    }

    private static double redondear(double valor) {
        return Math.round(valor * 100) / 100.0;
    }

    public Long getIdCotizacion() {
        return idCotizacion;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public int getDiasVigencia() {
        return diasVigencia;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getIgv() {
        return igv;
    }

    public double getTotal() {
        return total;
    }

    public String getCondiciones() {
        return condiciones;
    }
}
