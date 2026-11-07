package com.tecnolink.tecnolink.iam.infrastructure.persistence.jpa.mappers;

import com.tecnolink.tecnolink.iam.domain.model.aggregates.Administrator;
import com.tecnolink.tecnolink.iam.infrastructure.persistence.jpa.entities.AdministratorJpaEntity;

public final class AdministratorPersistenceMapper {

    private AdministratorPersistenceMapper() {
    }

    public static AdministratorJpaEntity toEntity(Administrator administrator) {
        return new AdministratorJpaEntity(administrator.getId(), administrator.getFirstName(),
                administrator.getLastName(), administrator.getEmail(), administrator.getPassword(),
                administrator.getPhone(), administrator.getRegisteredOn(), administrator.isActive(),
                administrator.getPosition());
    }

    public static Administrator toDomain(AdministratorJpaEntity entity) {
        return new Administrator(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getEmail(),
                entity.getPassword(), entity.getPhone(), entity.getRegisteredOn(), entity.isActive(),
                entity.getPosition());
    }
}
