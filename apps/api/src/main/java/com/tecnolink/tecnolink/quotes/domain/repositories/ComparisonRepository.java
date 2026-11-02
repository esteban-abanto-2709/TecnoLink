package com.tecnolink.tecnolink.quotes.domain.repositories;

import com.tecnolink.tecnolink.quotes.domain.aggregates.Comparison;

import java.util.List;
import java.util.Optional;

public interface ComparisonRepository {
    Comparison save(Comparison comparison);
    Comparison update(Comparison comparison);
    Optional<Comparison> findById(Long id);
    List<Comparison> findByClientId(String clientId);
}
