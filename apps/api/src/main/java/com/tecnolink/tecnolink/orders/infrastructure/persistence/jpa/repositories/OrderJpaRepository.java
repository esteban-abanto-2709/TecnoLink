package com.tecnolink.tecnolink.orders.infrastructure.persistence.jpa.repositories;

import com.tecnolink.tecnolink.orders.infrastructure.persistence.jpa.entities.OrderJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderJpaEntity, Long> {
}
