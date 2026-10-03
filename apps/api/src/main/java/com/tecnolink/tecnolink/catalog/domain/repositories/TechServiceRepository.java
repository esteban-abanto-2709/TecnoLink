package com.tecnolink.tecnolink.catalog.domain.repositories;

import com.tecnolink.tecnolink.catalog.domain.model.aggregates.TechService;

import java.util.List;
import java.util.Optional;

public interface TechServiceRepository {
    List<TechService> findAll();
    Optional<TechService> findById(String id);
    TechService save(TechService techService);
}
