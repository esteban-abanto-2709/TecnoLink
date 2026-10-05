package com.tecnolink.tecnolink.proveedores.infrastructure.persistence.memory;

import com.tecnolink.tecnolink.proveedores.domain.aggregates.Resena;
import com.tecnolink.tecnolink.proveedores.domain.repositories.ResenaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryResenaRepository implements ResenaRepository {

    private final Map<Long, Resena> store = new LinkedHashMap<>();
    private final AtomicLong sequence = new AtomicLong(7);

    public InMemoryResenaRepository() {
        save(new Resena(1L, "demo-client", "lenovo-ideapad-3", 5,
                "Rápida para la oficina y la batería dura toda la jornada.", LocalDate.of(2026, 6, 1)));
        save(new Resena(2L, "marco-t", "lenovo-ideapad-3", 4,
                "Buena laptop, aunque la pantalla podría tener más brillo.", LocalDate.of(2026, 7, 3)));
        save(new Resena(3L, "lucia-r", "asus-tuf-a15", 5,
                "Corre todo lo que uso para render sin problemas.", LocalDate.of(2026, 7, 21)));
        save(new Resena(4L, "marco-t", "lg-ultragear-24", 4,
                "Colores correctos y los 144 Hz se notan bastante.", LocalDate.of(2026, 8, 25)));
        save(new Resena(5L, "andrea-p", "mantenimiento-laptop", 5,
                "Puntuales y la laptop quedó como nueva, muy recomendados.", LocalDate.of(2026, 8, 30)));
        save(new Resena(6L, "lucia-r", "samsung-galaxy-a55", 3,
                "Buen celular, pero la entrega demoró más de lo prometido.", LocalDate.of(2026, 9, 12)));
    }

    @Override
    public Resena save(Resena resena) {
        store.put(resena.getIdResena(), resena);
        return resena;
    }

    @Override
    public List<Resena> findByPublicacionIds(Collection<String> publicacionIds) {
        List<Resena> resenas = new ArrayList<>();
        for (Resena resena : store.values()) {
            if (publicacionIds.contains(resena.getPublicacionId())) resenas.add(resena);
        }
        return resenas;
    }

    @Override
    public List<Resena> findByClienteId(String clienteId) {
        List<Resena> resenas = new ArrayList<>();
        for (Resena resena : store.values()) {
            if (resena.getClienteId().equals(clienteId)) resenas.add(resena);
        }
        return resenas;
    }

    @Override
    public boolean existsByClienteIdAndPublicacionId(String clienteId, String publicacionId) {
        for (Resena resena : store.values()) {
            if (resena.getClienteId().equals(clienteId) && resena.getPublicacionId().equals(publicacionId)) return true;
        }
        return false;
    }

    @Override
    public Long nextId() {
        return sequence.getAndIncrement();
    }
}
