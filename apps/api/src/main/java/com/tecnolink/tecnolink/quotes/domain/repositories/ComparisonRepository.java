package com.tecnolink.tecnolink.quotes.domain.repositories;

import com.tecnolink.tecnolink.quotes.domain.model.aggregates.Comparison;

import java.util.Optional;

public interface ComparisonRepository {
    Optional<Comparison> findById(Long id);
    Comparison save(Comparison comparison);
}
