package com.tecnolink.tecnolink.quotes.domain.entities;

import java.time.LocalDate;

public class Quote {
    public static final double IGV_RATE = 0.18;

    private final Long id;
    private final LocalDate issueDate;
    private final int validityDays;
    private final double subtotal;
    private final double igv;
    private final double total;
    private final String conditions;

    public Quote(LocalDate issueDate, int validityDays, double subtotal, String conditions) {
        this(null, issueDate, validityDays, subtotal, igvOf(subtotal), round(subtotal + igvOf(subtotal)), conditions);
    }

    public Quote(Long id, LocalDate issueDate, int validityDays, double subtotal, double igv, double total,
                 String conditions) {
        if (issueDate == null) throw new IllegalArgumentException("Issue date is required");
        if (validityDays < 1) throw new IllegalArgumentException("Validity must be at least 1 day");
        if (subtotal <= 0) throw new IllegalArgumentException("Subtotal must be greater than 0");
        if (conditions == null || conditions.isBlank()) throw new IllegalArgumentException("Conditions are required");
        this.id = id;
        this.issueDate = issueDate;
        this.validityDays = validityDays;
        this.subtotal = subtotal;
        this.igv = igv;
        this.total = total;
        this.conditions = conditions.trim();
    }

    public LocalDate validUntil() {
        return issueDate.plusDays(validityDays);
    }

    public boolean isValidOn(LocalDate date) {
        return !date.isAfter(validUntil());
    }

    private static double igvOf(double subtotal) {
        return round(subtotal * IGV_RATE);
    }

    private static double round(double value) {
        return Math.round(value * 100) / 100.0;
    }

    public Long getId() {
        return id;
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
