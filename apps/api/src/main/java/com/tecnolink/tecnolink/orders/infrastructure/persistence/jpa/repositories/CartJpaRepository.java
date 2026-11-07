package com.tecnolink.tecnolink.orders.infrastructure.persistence.jpa.repositories;

import com.tecnolink.tecnolink.orders.infrastructure.persistence.jpa.entities.CartJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartJpaRepository extends JpaRepository<CartJpaEntity, Long> {
}
