package com.tecnolink.tecnolink.cotizaciones.domain.repositories;

import com.tecnolink.tecnolink.cotizaciones.domain.aggregates.SolicitudCotizacion;

import java.util.List;
import java.util.Optional;

public interface SolicitudCotizacionRepository {
    SolicitudCotizacion save(SolicitudCotizacion solicitud);
    Optional<SolicitudCotizacion> findById(Long idSolicitud);
    List<SolicitudCotizacion> findByClienteId(String clienteId);
    List<SolicitudCotizacion> findByPublicacionId(String publicacionId);
    Long nextId();
}
