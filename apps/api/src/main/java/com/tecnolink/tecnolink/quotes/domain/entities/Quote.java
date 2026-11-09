package com.tecnolink.tecnolink.quotes.domain.entities;

import com.tecnolink.tecnolink.quotes.domain.valueobjects.QuoteAmounts;

import java.time.LocalDate;

public class Quote {
    private final Long id;
    private final LocalDate issueDate;
    private final int validityDays;
    private final QuoteAmounts amounts;
    private final String conditions;
    private final String notes;

    public Quote(LocalDate issueDate, int validityDays, QuoteAmounts amounts, String conditions, String notes) {
        this(null, issueDate, validityDays, amounts, conditions, notes);
    }

    public Quote(Long id, LocalDate issueDate, int validityDays, QuoteAmounts amounts, String conditions,
                 String notes) {
        if (issueDate == null) throw new IllegalArgumentException("Issue date is required");
        if (validityDays < 1) throw new IllegalArgumentException("Validity must be at least 1 day");
        if (amounts == null) throw new IllegalArgumentException("Amounts are required");
        if (conditions == null || conditions.isBlank()) throw new IllegalArgumentException("Conditions are required");
        this.id = id;
        this.issueDate = issueDate;
        this.validityDays = validityDays;
        this.amounts = amounts;
        this.conditions = conditions.trim();
        this.notes = notes == null || notes.isBlank() ? null : notes.trim();
    }

    public LocalDate validUntil() {
        return issueDate.plusDays(validityDays);
    }

    public boolean isValidOn(LocalDate date) {
        return !date.isAfter(validUntil());
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
        return amounts.subtotal();
    }

    public double getIgv() {
        return amounts.igv();
    }

    public double getTotal() {
        return amounts.total();
    }

    public String getConditions() {
        return conditions;
    }

    public String getNotes() {
        return notes;
    }
}
