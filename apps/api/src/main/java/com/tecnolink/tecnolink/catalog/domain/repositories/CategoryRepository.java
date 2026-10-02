package com.tecnolink.tecnolink.catalog.domain.repositories;

import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    List<Category> findAll();
    Optional<Category> findById(String id);
}
