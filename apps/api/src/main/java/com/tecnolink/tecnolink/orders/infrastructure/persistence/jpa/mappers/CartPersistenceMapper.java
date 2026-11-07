package com.tecnolink.tecnolink.orders.infrastructure.persistence.jpa.mappers;

import com.tecnolink.tecnolink.orders.domain.model.aggregates.Cart;
import com.tecnolink.tecnolink.orders.domain.model.entities.CartItem;
import com.tecnolink.tecnolink.orders.infrastructure.persistence.jpa.entities.CartItemJpaEntity;
import com.tecnolink.tecnolink.orders.infrastructure.persistence.jpa.entities.CartJpaEntity;

public final class CartPersistenceMapper {

    private CartPersistenceMapper() {
    }

    public static CartJpaEntity toEntity(Cart cart) {
        return new CartJpaEntity(cart.getId(), cart.getClientId(), cart.getItems().stream()
                .map(item -> new CartItemJpaEntity(item.getListingId(), item.getQuantity(), item.getUnitPrice()))
                .toList());
    }

    public static Cart toDomain(CartJpaEntity entity) {
        return new Cart(entity.getId(), entity.getClientId(), entity.getItems().stream()
                .map(item -> new CartItem(item.getListingId(), item.getQuantity(), item.getUnitPrice()))
                .toList());
    }
}
