package com.tecnolink.tecnolink.catalog.infrastructure.persistence.memory;

import com.tecnolink.tecnolink.catalog.domain.model.aggregates.TechService;
import com.tecnolink.tecnolink.catalog.domain.model.enums.ServicePricing;
import com.tecnolink.tecnolink.catalog.domain.repositories.TechServiceRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryTechServiceRepository implements TechServiceRepository {

    private final Map<String, TechService> store = new LinkedHashMap<>();

    public InMemoryTechServiceRepository() {
        save(new TechService("soporte-domicilio", "Soporte técnico a domicilio", "support", "soportelima", 60,
                ServicePricing.HOURLY,
                "Diagnóstico y solución de fallas de software o hardware en el lugar del cliente.",
                "Lima Metropolitana"));
        save(new TechService("mantenimiento-laptop", "Mantenimiento preventivo de laptop", "support", "soportelima", 90,
                ServicePricing.FIXED,
                "Limpieza interna, cambio de pasta térmica y revisión general del equipo.",
                "Surco, San Borja, Miraflores y alrededores"));
        save(new TechService("recuperacion-datos", "Recuperación de datos", "support", "compuzone", 150,
                ServicePricing.FROM,
                "Rescate de archivos en discos dañados o unidades formateadas por error.",
                "Solo en taller, Cercado de Lima"));
        save(new TechService("cableado-oficina", "Instalación de red cableada para oficina", "installation", "redesandinas", 450,
                ServicePricing.FROM,
                "Cableado estructurado, certificación de puntos y configuración de equipos.",
                "Lima Metropolitana y Callao"));
        save(new TechService("camaras-seguridad", "Instalación de cámaras de seguridad", "installation", "redesandinas", 600,
                ServicePricing.FROM,
                "Instalación de cámaras, grabador y acceso remoto desde el celular.",
                "Lima Metropolitana"));
        save(new TechService("web-informativa", "Desarrollo de página web informativa", "development", "digitalstore", 1200,
                ServicePricing.FROM,
                "Sitio de hasta cinco secciones, adaptable a celular, con dominio y hosting del primer año.",
                "Remoto"));
    }

    private void save(TechService service) {
        store.put(service.getId(), service);
    }

    @Override
    public List<TechService> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<TechService> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }
}
