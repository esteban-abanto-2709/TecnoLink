package com.tecnolink.tecnolink.cotizaciones.domain.aggregates;

import com.tecnolink.tecnolink.cotizaciones.domain.entities.Cotizacion;
import com.tecnolink.tecnolink.cotizaciones.domain.valueobjects.EstadoSolicitud;

import java.time.LocalDate;

public class SolicitudCotizacion {
    private final Long idSolicitud;
    private final String clienteId;
    private final String publicacionId;
    private LocalDate fecha;
    private final int cantidad;
    private final String requerimiento;
    private EstadoSolicitud estado;
    private Cotizacion cotizacion;

    public SolicitudCotizacion(Long idSolicitud, String clienteId, String publicacionId, int cantidad, String requerimiento) {
        this(idSolicitud, clienteId, publicacionId, null, cantidad, requerimiento, EstadoSolicitud.BORRADOR, null);
    }

    public SolicitudCotizacion(Long idSolicitud, String clienteId, String publicacionId, LocalDate fecha, int cantidad,
                               String requerimiento, EstadoSolicitud estado, Cotizacion cotizacion) {
        if (idSolicitud == null || idSolicitud < 1) {
            throw new IllegalArgumentException("Request id must be positive");
        }
        if (clienteId == null || clienteId.isBlank()) {
            throw new IllegalArgumentException("Request client is required");
        }
        if (publicacionId == null || publicacionId.isBlank()) {
            throw new IllegalArgumentException("Request listing is required");
        }
        if (cantidad < 1) {
            throw new IllegalArgumentException("Request quantity must be at least 1");
        }
        if (requerimiento == null || requerimiento.isBlank()) {
            throw new IllegalArgumentException("Request requirement is required");
        }
        if (estado == null) {
            throw new IllegalArgumentException("Request status is required");
        }
        if (estado != EstadoSolicitud.BORRADOR && fecha == null) {
            throw new IllegalArgumentException("A sent request needs a date");
        }
        if ((estado == EstadoSolicitud.RESPONDIDA || estado == EstadoSolicitud.VENCIDA) && cotizacion == null) {
            throw new IllegalArgumentException("An answered request needs a quote");
        }
        this.idSolicitud = idSolicitud;
        this.clienteId = clienteId;
        this.publicacionId = publicacionId;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.requerimiento = requerimiento;
        this.estado = estado;
        this.cotizacion = cotizacion;
    }

    public void enviar(LocalDate hoy) {
        if (estado != EstadoSolicitud.BORRADOR) {
            throw new IllegalArgumentException("Only a draft request can be sent");
        }
        estado = EstadoSolicitud.ENVIADA;
        fecha = hoy;
    }

    public Cotizacion responder(LocalDate hoy, int diasVigencia, double subtotal, String condiciones) {
        if (estado != EstadoSolicitud.ENVIADA) {
            throw new IllegalArgumentException("Only a sent request can be answered");
        }
        cotizacion = new Cotizacion(idSolicitud, hoy, diasVigencia, subtotal, condiciones);
        estado = EstadoSolicitud.RESPONDIDA;
        return cotizacion;
    }

    public void vencer(LocalDate hoy) {
        if (estado == EstadoSolicitud.RESPONDIDA && !cotizacion.estaVigente(hoy)) {
            estado = EstadoSolicitud.VENCIDA;
        }
    }

    public Long getIdSolicitud() {
        return idSolicitud;
    }

    public String getClienteId() {
        return clienteId;
    }

    public String getPublicacionId() {
        return publicacionId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getRequerimiento() {
        return requerimiento;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }
}
