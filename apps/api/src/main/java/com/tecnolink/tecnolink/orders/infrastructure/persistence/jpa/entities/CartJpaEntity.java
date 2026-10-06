package com.tecnolink.tecnolink.orders.infrastructure.persistence.jpa.entities;

import com.tecnolink.tecnolink.iam.infrastructure.persistence.jpa.entities.ClientJpaEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class CartJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id", nullable = false, unique = true)
    private String clientId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private ClientJpaEntity client;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id")
    private List<CartItemJpaEntity> items = new ArrayList<>();

    protected CartJpaEntity() {
    }

    public CartJpaEntity(Long id, String clientId, List<CartItemJpaEntity> items) {
        this.id = id;
        this.clientId = clientId;
        this.items = new ArrayList<>(items);
    }

    public Long getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public List<CartItemJpaEntity> getItems() {
        return items;
    }
}
