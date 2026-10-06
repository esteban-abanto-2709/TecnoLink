package com.tecnolink.tecnolink.quotes.domain.model.aggregates;

import java.time.LocalDate;

public class Quote {
    private final Long id;
    private final Long requestId;
    private final LocalDate issueDate;
    private final int validityDays;
    private final double subtotal;
    private final double igv;
    private final double total;
    private final String conditions;

    public Quote(Long id, Long requestId, LocalDate issueDate, int validityDays, double subtotal, double igv,
                 double total, String conditions) {
        if (requestId == null) {
            throw new IllegalArgumentException("Quote request is required");
        }
        if (issueDate == null) {
            throw new IllegalArgumentException("Quote issue date is required");
        }
        if (validityDays <= 0) {
            throw new IllegalArgumentException("Quote validity days must be greater than zero");
        }
        if (subtotal < 0 || igv < 0 || total < 0) {
            throw new IllegalArgumentException("Quote amounts cannot be negative");
        }
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
