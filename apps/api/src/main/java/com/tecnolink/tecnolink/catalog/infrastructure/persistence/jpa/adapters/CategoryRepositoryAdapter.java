package com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.adapters;

import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Category;
import com.tecnolink.tecnolink.catalog.domain.repositories.CategoryRepository;
import com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.mappers.CategoryPersistenceMapper;
import com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.repositories.CategoryJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryAdapter implements CategoryRepository {

    private final CategoryJpaRepository jpaRepository;

    public CategoryRepositoryAdapter(CategoryJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Category> findAll() {
        return jpaRepository.findAll().stream()
                .map(CategoryPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Category> findById(String id) {
        return jpaRepository.findById(id).map(CategoryPersistenceMapper::toDomain);
    }

    @Override
    public Category save(Category category) {
        return CategoryPersistenceMapper.toDomain(jpaRepository.save(CategoryPersistenceMapper.toEntity(category)));
    }
}
