package com.tecnolink.tecnolink.quotes.domain.repositories;

import com.tecnolink.tecnolink.quotes.domain.aggregates.Specification;

import java.util.List;
import java.util.Optional;

public interface SpecificationRepository {
    Specification save(Specification specification);
    Optional<Specification> findById(Long id);
    List<Specification> findByProductId(String productId);
    List<Specification> findByProductIdIn(List<String> productIds);
    boolean existsByProductIdAndName(String productId, String name);
}
