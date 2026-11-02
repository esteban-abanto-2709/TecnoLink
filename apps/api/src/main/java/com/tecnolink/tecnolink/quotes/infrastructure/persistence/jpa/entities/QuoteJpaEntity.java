package com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quotes")
public class QuoteJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "request_id", nullable = false, unique = true)
    private QuoteRequestJpaEntity request;

    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;

    @Column(name = "validity_days", nullable = false)
    private int validityDays;

    @Column(nullable = false)
    private double subtotal;

    @Column(nullable = false)
    private double igv;

    @Column(nullable = false)
    private double total;

    @Column(nullable = false, length = 1000)
    private String conditions;
}
