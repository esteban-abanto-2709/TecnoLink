package com.tecnolink.tecnolink.orders.domain.repositories;

import com.tecnolink.tecnolink.orders.domain.model.aggregates.Order;

import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findById(Long id);
    Order save(Order order);
}
