package com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.entities;

import com.tecnolink.tecnolink.iam.infrastructure.persistence.jpa.entities.ClientJpaEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comparisons", indexes = {@Index(name = "ix_comparison_client", columnList = "client_id")})
public class ComparisonJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id", nullable = false)
    private String clientId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private ClientJpaEntity client;

    @Column(name = "comparison_date", nullable = false, updatable = false)
    private LocalDate createdOn;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "comparison_products", joinColumns = @JoinColumn(name = "comparison_id"))
    @OrderColumn(name = "position")
    @Column(name = "product_id", nullable = false)
    private List<String> productIds = new ArrayList<>();
}
