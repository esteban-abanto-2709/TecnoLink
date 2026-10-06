package com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "quotes")
public class QuoteJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request_id", nullable = false, unique = true)
    private Long requestId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id", insertable = false, updatable = false)
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

    @Column(length = 1000)
    private String conditions;

    protected QuoteJpaEntity() {
    }

    public QuoteJpaEntity(Long id, Long requestId, LocalDate issueDate, int validityDays, double subtotal,
                          double igv, double total, String conditions) {
        this.id = id;
        this.requestId = requestId;
        this.issueDate = issueDate;
        this.validityDays = validityDays;
        this.subtotal = subtotal;
        this.igv = igv;
        this.total = total;
        this.conditions = conditions;
    }

    public Long getId() {
        return id;
    }

    public Long getRequestId() {
        return requestId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public int getValidityDays() {
        return validityDays;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getIgv() {
        return igv;
    }

    public double getTotal() {
        return total;
    }

    public String getConditions() {
        return conditions;
    }
}
