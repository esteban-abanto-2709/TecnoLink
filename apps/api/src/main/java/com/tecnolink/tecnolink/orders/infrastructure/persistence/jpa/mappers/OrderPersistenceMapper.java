package com.tecnolink.tecnolink.orders.infrastructure.persistence.jpa.mappers;

import com.tecnolink.tecnolink.orders.domain.model.aggregates.Order;
import com.tecnolink.tecnolink.orders.domain.model.entities.OrderDetail;
import com.tecnolink.tecnolink.orders.infrastructure.persistence.jpa.entities.OrderDetailJpaEntity;
import com.tecnolink.tecnolink.orders.infrastructure.persistence.jpa.entities.OrderJpaEntity;

public final class OrderPersistenceMapper {

    private OrderPersistenceMapper() {
    }

    public static OrderJpaEntity toEntity(Order order) {
        return new OrderJpaEntity(order.getId(), order.getClientId(), order.getDate(), order.getTotal(),
                order.getStatus(), order.getSimulatedPaymentMethod(), order.getDetails().stream()
                .map(detail -> new OrderDetailJpaEntity(detail.getListingId(), detail.getQuantity(),
                        detail.getUnitPrice(), detail.getSubtotal()))
                .toList());
    }

    public static Order toDomain(OrderJpaEntity entity) {
        return new Order(entity.getId(), entity.getClientId(), entity.getDate(), entity.getTotal(),
                entity.getStatus(), entity.getSimulatedPaymentMethod(), entity.getDetails().stream()
                .map(detail -> new OrderDetail(detail.getListingId(), detail.getQuantity(),
                        detail.getUnitPrice(), detail.getSubtotal()))
                .toList());
    }
}
