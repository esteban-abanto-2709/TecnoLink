package com.tecnolink.tecnolink.orders.infrastructure.persistence.jpa.adapters;

import com.tecnolink.tecnolink.orders.domain.model.aggregates.Order;
import com.tecnolink.tecnolink.orders.domain.repositories.OrderRepository;
import com.tecnolink.tecnolink.orders.infrastructure.persistence.jpa.mappers.OrderPersistenceMapper;
import com.tecnolink.tecnolink.orders.infrastructure.persistence.jpa.repositories.OrderJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrderRepositoryAdapter implements OrderRepository {

    private final OrderJpaRepository jpaRepository;

    public OrderRepositoryAdapter(OrderJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return jpaRepository.findById(id).map(OrderPersistenceMapper::toDomain);
    }

    @Override
    public Order save(Order order) {
        return OrderPersistenceMapper.toDomain(jpaRepository.save(OrderPersistenceMapper.toEntity(order)));
    }
}
