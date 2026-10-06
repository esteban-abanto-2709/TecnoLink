package com.tecnolink.tecnolink.iam.infrastructure.persistence.jpa.repositories;

import com.tecnolink.tecnolink.iam.infrastructure.persistence.jpa.entities.ClientJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientJpaRepository extends JpaRepository<ClientJpaEntity, String> {
}
