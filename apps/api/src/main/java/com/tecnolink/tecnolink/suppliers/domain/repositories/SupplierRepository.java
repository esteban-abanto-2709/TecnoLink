package com.tecnolink.tecnolink.suppliers.domain.repositories;

import com.tecnolink.tecnolink.suppliers.domain.model.aggregates.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository {
    List<Supplier> findAll();
    Optional<Supplier> findById(String id);
    Supplier save(Supplier supplier);
}
