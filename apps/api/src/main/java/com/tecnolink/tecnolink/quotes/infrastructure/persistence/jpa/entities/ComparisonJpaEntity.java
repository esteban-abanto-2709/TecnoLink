package com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.entities;

import com.tecnolink.tecnolink.iam.infrastructure.persistence.jpa.entities.ClientJpaEntity;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comparisons")
public class ComparisonJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id", nullable = false)
    private String clientId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private ClientJpaEntity client;

    @Column(name = "comparison_date", nullable = false)
    private LocalDate date;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "comparison_products", joinColumns = @JoinColumn(name = "comparison_id"))
    @Column(name = "product_id")
    private List<String> productIds = new ArrayList<>();

    protected ComparisonJpaEntity() {
    }

    public ComparisonJpaEntity(Long id, String clientId, LocalDate date, List<String> productIds) {
        this.id = id;
        this.clientId = clientId;
        this.date = date;
        this.productIds = new ArrayList<>(productIds);
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

    public List<String> getProductIds() {
        return productIds;
    }
}
