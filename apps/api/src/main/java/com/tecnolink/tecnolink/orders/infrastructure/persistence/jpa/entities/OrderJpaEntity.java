package com.tecnolink.tecnolink.orders.infrastructure.persistence.jpa.entities;

import com.tecnolink.tecnolink.iam.infrastructure.persistence.jpa.entities.ClientJpaEntity;
import com.tecnolink.tecnolink.orders.domain.model.enums.OrderStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id", nullable = false)
    private String clientId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private ClientJpaEntity client;

    @Column(name = "order_date", nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private double total;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(name = "simulated_payment_method")
    private String simulatedPaymentMethod;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private List<OrderDetailJpaEntity> details = new ArrayList<>();

    protected OrderJpaEntity() {
    }

    public OrderJpaEntity(Long id, String clientId, LocalDate date, double total, OrderStatus status,
                          String simulatedPaymentMethod, List<OrderDetailJpaEntity> details) {
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

    public List<OrderDetailJpaEntity> getDetails() {
        return details;
    }
}
