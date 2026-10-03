package com.tecnolink.tecnolink.catalog.domain.repositories;

import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();
    Optional<Product> findById(String id);
    Product save(Product product);
}
