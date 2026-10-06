package com.tecnolink.tecnolink.orders.domain.model.aggregates;

import com.tecnolink.tecnolink.orders.domain.model.entities.CartItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private final Long id;
    private final String clientId;
    private final List<CartItem> items;

    public Cart(Long id, String clientId, List<CartItem> items) {
        if (clientId == null || clientId.isBlank()) {
            throw new IllegalArgumentException("Cart client is required");
        }
        this.id = id;
        this.clientId = clientId;
        this.items = new ArrayList<>(items == null ? List.of() : items);
    }

    public Long getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }
}
