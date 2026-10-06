package com.tecnolink.tecnolink.suppliers.infrastructure.persistence.jpa.mappers;

import com.tecnolink.tecnolink.suppliers.domain.model.aggregates.Supplier;
import com.tecnolink.tecnolink.suppliers.infrastructure.persistence.jpa.entities.SupplierJpaEntity;

public final class SupplierPersistenceMapper {

    private SupplierPersistenceMapper() {
    }

    public static SupplierJpaEntity toEntity(Supplier supplier) {
        return new SupplierJpaEntity(supplier.getId(), supplier.getFirstName(), supplier.getLastName(),
                supplier.getEmail(), supplier.getPassword(), supplier.getPhone(), supplier.getRegisteredOn(),
                supplier.isActive(), supplier.getRuc(), supplier.getName(), supplier.getRegion(),
                supplier.getDistrict(), supplier.getDescription(), supplier.getSince(), supplier.getReputation(),
                supplier.getStatus(), supplier.getReviewNote(), supplier.getReviewedOn());
    }

    public static Supplier toDomain(SupplierJpaEntity entity) {
        return new Supplier(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getEmail(),
                entity.getPassword(), entity.getPhone(), entity.getRegisteredOn(), entity.isActive(),
                entity.getRuc(), entity.getName(), entity.getRegion(), entity.getDistrict(),
                entity.getDescription(), entity.getSince(), entity.getReputation(), entity.getStatus(),
                entity.getReviewNote(), entity.getReviewedOn());
    }
}
