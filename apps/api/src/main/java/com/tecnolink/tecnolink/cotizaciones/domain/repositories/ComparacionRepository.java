package com.tecnolink.tecnolink.cotizaciones.domain.repositories;

import com.tecnolink.tecnolink.cotizaciones.domain.aggregates.Comparacion;

import java.util.List;
import java.util.Optional;

public interface ComparacionRepository {
    Comparacion save(Comparacion comparacion);
    Optional<Comparacion> findById(Long idComparacion);
    List<Comparacion> findByClienteId(String clienteId);
    Long nextId();
}
