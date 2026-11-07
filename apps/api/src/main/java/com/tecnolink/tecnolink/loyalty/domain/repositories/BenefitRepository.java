package com.tecnolink.tecnolink.loyalty.domain.repositories;

import com.tecnolink.tecnolink.loyalty.domain.model.aggregates.Benefit;

import java.util.List;
import java.util.Optional;

public interface BenefitRepository {
    List<Benefit> findAll();
    Optional<Benefit> findById(String id);
}
