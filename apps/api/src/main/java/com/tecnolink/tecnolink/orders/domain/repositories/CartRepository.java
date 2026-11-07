package com.tecnolink.tecnolink.orders.domain.repositories;

import com.tecnolink.tecnolink.orders.domain.model.aggregates.Cart;

import java.util.Optional;

public interface CartRepository {
    Optional<Cart> findById(Long id);
    Cart save(Cart cart);
}
