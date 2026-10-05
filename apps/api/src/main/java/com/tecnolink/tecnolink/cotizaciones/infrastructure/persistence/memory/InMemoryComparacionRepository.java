package com.tecnolink.tecnolink.cotizaciones.infrastructure.persistence.memory;

import com.tecnolink.tecnolink.cotizaciones.domain.aggregates.Comparacion;
import com.tecnolink.tecnolink.cotizaciones.domain.repositories.ComparacionRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryComparacionRepository implements ComparacionRepository {

    private final Map<Long, Comparacion> store = new LinkedHashMap<>();
    private final AtomicLong sequence = new AtomicLong(3);

    public InMemoryComparacionRepository() {
        save(new Comparacion(1L, "demo-client", LocalDate.of(2026, 9, 27),
                List.of("lenovo-ideapad-3", "hp-pavilion-14", "acer-aspire-3")));
        save(new Comparacion(2L, "demo-client", LocalDate.of(2026, 10, 1),
                List.of("asus-tuf-a15", "hp-pavilion-14")));
    }

    @Override
    public Comparacion save(Comparacion comparacion) {
        store.put(comparacion.getIdComparacion(), comparacion);
        return comparacion;
    }

    @Override
    public Optional<Comparacion> findById(Long idComparacion) {
        return Optional.ofNullable(store.get(idComparacion));
    }

    @Override
    public List<Comparacion> findByClienteId(String clienteId) {
        List<Comparacion> comparaciones = new ArrayList<>();
        for (Comparacion comparacion : store.values()) {
            if (comparacion.getClienteId().equals(clienteId)) comparaciones.add(comparacion);
        }
        return comparaciones;
    }

    @Override
    public Long nextId() {
        return sequence.getAndIncrement();
    }
}
