package com.tecnolink.tecnolink.cotizaciones.infrastructure.persistence.memory;

import com.tecnolink.tecnolink.cotizaciones.domain.aggregates.SolicitudCotizacion;
import com.tecnolink.tecnolink.cotizaciones.domain.entities.Cotizacion;
import com.tecnolink.tecnolink.cotizaciones.domain.repositories.SolicitudCotizacionRepository;
import com.tecnolink.tecnolink.cotizaciones.domain.valueobjects.EstadoSolicitud;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemorySolicitudCotizacionRepository implements SolicitudCotizacionRepository {

    private final Map<Long, SolicitudCotizacion> store = new LinkedHashMap<>();
    private final AtomicLong sequence = new AtomicLong(5);

    public InMemorySolicitudCotizacionRepository() {
        save(new SolicitudCotizacion(1L, "demo-client", "lenovo-ideapad-3", LocalDate.of(2026, 9, 28), 10,
                "Laptops para oficina contable, entrega en Lima con factura",
                EstadoSolicitud.RESPONDIDA,
                new Cotizacion(1L, LocalDate.of(2026, 9, 29), 15, 17990.0,
                        "Precio por 10 unidades. Garantía de 12 meses. Entrega en 3 días hábiles")));
        save(new SolicitudCotizacion(2L, "demo-client", "lg-ultragear-24", LocalDate.of(2026, 10, 2), 4,
                "Monitores para sala de diseño, se requiere instalación", EstadoSolicitud.ENVIADA, null));
        save(new SolicitudCotizacion(3L, "demo-client", "mantenimiento-laptop", LocalDate.of(2026, 8, 10), 20,
                "Mantenimiento preventivo de 20 laptops en nuestra sede de Miraflores",
                EstadoSolicitud.VENCIDA,
                new Cotizacion(3L, LocalDate.of(2026, 8, 11), 7, 1800.0,
                        "Servicio en sede del cliente. Incluye limpieza interna y cambio de pasta térmica")));
        save(new SolicitudCotizacion(4L, "demo-client", "epson-l3250", 2,
                "Impresoras para dos oficinas, consultar disponibilidad"));
    }

    @Override
    public SolicitudCotizacion save(SolicitudCotizacion solicitud) {
        store.put(solicitud.getIdSolicitud(), solicitud);
        return solicitud;
    }

    @Override
    public Optional<SolicitudCotizacion> findById(Long idSolicitud) {
        return Optional.ofNullable(store.get(idSolicitud));
    }

    @Override
    public List<SolicitudCotizacion> findByClienteId(String clienteId) {
        List<SolicitudCotizacion> solicitudes = new ArrayList<>();
        for (SolicitudCotizacion solicitud : store.values()) {
            if (solicitud.getClienteId().equals(clienteId)) solicitudes.add(solicitud);
        }
        return solicitudes;
    }

    @Override
    public List<SolicitudCotizacion> findByPublicacionId(String publicacionId) {
        List<SolicitudCotizacion> solicitudes = new ArrayList<>();
        for (SolicitudCotizacion solicitud : store.values()) {
            if (solicitud.getPublicacionId().equals(publicacionId)) solicitudes.add(solicitud);
        }
        return solicitudes;
    }

    @Override
    public Long nextId() {
        return sequence.getAndIncrement();
    }
}
