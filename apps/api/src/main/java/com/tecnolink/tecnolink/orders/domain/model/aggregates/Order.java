package com.tecnolink.tecnolink.orders.domain.model.aggregates;

import com.tecnolink.tecnolink.orders.domain.model.entities.OrderDetail;
import com.tecnolink.tecnolink.orders.domain.model.enums.OrderStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private final Long id;
    private final String clientId;
    private final LocalDate date;
    private final double total;
    private final OrderStatus status;
    private final String simulatedPaymentMethod;
    private final List<OrderDetail> details;

    public Order(Long id, String clientId, LocalDate date, double total, OrderStatus status,
                 String simulatedPaymentMethod, List<OrderDetail> details) {
        if (clientId == null || clientId.isBlank()) {
            throw new IllegalArgumentException("Order client is required");
        }
        if (date == null) {
            throw new IllegalArgumentException("Order date is required");
        }
        if (total < 0) {
            throw new IllegalArgumentException("Order total cannot be negative");
        }
        if (status == null) {
            throw new IllegalArgumentException("Order status is required");
        }
        if (details == null || details.isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one detail");
        }
        this.id = id;
        this.clientId = clientId;
        this.date = date;
        this.total = total;
        this.status = status;
        this.simulatedPaymentMethod = simulatedPaymentMethod;
        this.details = new ArrayList<>(details);
    }

    public Long getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getTotal() {
        return total;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getSimulatedPaymentMethod() {
        return simulatedPaymentMethod;
    }

    public List<OrderDetail> getDetails() {
        return Collections.unmodifiableList(details);
    }
}
