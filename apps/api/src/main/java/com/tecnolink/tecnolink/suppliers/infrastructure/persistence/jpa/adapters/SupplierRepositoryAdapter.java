package com.tecnolink.tecnolink.suppliers.infrastructure.persistence.jpa.adapters;

import com.tecnolink.tecnolink.suppliers.domain.model.aggregates.Supplier;
import com.tecnolink.tecnolink.suppliers.domain.repositories.SupplierRepository;
import com.tecnolink.tecnolink.suppliers.infrastructure.persistence.jpa.mappers.SupplierPersistenceMapper;
import com.tecnolink.tecnolink.suppliers.infrastructure.persistence.jpa.repositories.SupplierJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SupplierRepositoryAdapter implements SupplierRepository {

    private final SupplierJpaRepository jpaRepository;

    public SupplierRepositoryAdapter(SupplierJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Supplier> findAll() {
        return jpaRepository.findAll().stream()
                .map(SupplierPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Supplier> findById(String id) {
        return jpaRepository.findById(id).map(SupplierPersistenceMapper::toDomain);
    }

    @Override
    public Supplier save(Supplier supplier) {
        return SupplierPersistenceMapper.toDomain(jpaRepository.save(SupplierPersistenceMapper.toEntity(supplier)));
    }
}
