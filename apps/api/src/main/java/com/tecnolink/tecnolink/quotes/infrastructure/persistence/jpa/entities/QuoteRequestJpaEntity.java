package com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.entities;

import com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.entities.ListingJpaEntity;
import com.tecnolink.tecnolink.iam.infrastructure.persistence.jpa.entities.ClientJpaEntity;
import com.tecnolink.tecnolink.quotes.domain.valueobjects.QuoteRequestStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quote_requests", indexes = {@Index(name = "ix_quote_request_client", columnList = "client_id"), @Index(name = "ix_quote_request_listing", columnList = "listing_id")})
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

    @Column(name = "request_date", nullable = false, updatable = false)
    private LocalDate requestedOn;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false, length = 1000)
    private String requirement;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private QuoteRequestStatus status;
}
