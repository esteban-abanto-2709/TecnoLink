package com.tecnolink.tecnolink.proveedores.domain.repositories;

import com.tecnolink.tecnolink.proveedores.domain.aggregates.Resena;

import java.util.Collection;
import java.util.List;

public interface ResenaRepository {
    Resena save(Resena resena);
    List<Resena> findByPublicacionIds(Collection<String> publicacionIds);
    List<Resena> findByClienteId(String clienteId);
    boolean existsByClienteIdAndPublicacionId(String clienteId, String publicacionId);
    Long nextId();
}
