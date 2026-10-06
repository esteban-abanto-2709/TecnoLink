package com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.entities;

import com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.entities.ListingJpaEntity;
import com.tecnolink.tecnolink.iam.infrastructure.persistence.jpa.entities.ClientJpaEntity;
import com.tecnolink.tecnolink.quotes.domain.model.enums.QuoteRequestStatus;
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
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "quote_requests")
public class QuoteRequestJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id", nullable = false)
    private String clientId;

    @Column(name = "listing_id", nullable = false)
    private String listingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private ClientJpaEntity client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "listing_id", insertable = false, updatable = false)
    private ListingJpaEntity listing;

    @Column(name = "request_date", nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private int quantity;

    @Column(length = 1000)
    private String requirement;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuoteRequestStatus status;

    protected QuoteRequestJpaEntity() {
    }

    public QuoteRequestJpaEntity(Long id, String clientId, String listingId, LocalDate date, int quantity,
                                 String requirement, QuoteRequestStatus status) {
        this.id = id;
        this.clientId = clientId;
        this.listingId = listingId;
        this.date = date;
        this.quantity = quantity;
        this.requirement = requirement;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public String getListingId() {
        return listingId;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getRequirement() {
        return requirement;
    }

    public QuoteRequestStatus getStatus() {
        return status;
    }
}
