package com.tecnolink.tecnolink.quotes.domain.model.aggregates;

import com.tecnolink.tecnolink.quotes.domain.model.enums.QuoteRequestStatus;

import java.time.LocalDate;

public class QuoteRequest {
    private final Long id;
    private final String clientId;
    private final String listingId;
    private final LocalDate date;
    private final int quantity;
    private final String requirement;
    private final QuoteRequestStatus status;

    public QuoteRequest(Long id, String clientId, String listingId, LocalDate date, int quantity,
                        String requirement, QuoteRequestStatus status) {
        if (clientId == null || clientId.isBlank()) {
            throw new IllegalArgumentException("Quote request client is required");
        }
        if (listingId == null || listingId.isBlank()) {
            throw new IllegalArgumentException("Quote request listing is required");
        }
        if (date == null) {
            throw new IllegalArgumentException("Quote request date is required");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quote request quantity must be greater than zero");
        }
        if (status == null) {
            throw new IllegalArgumentException("Quote request status is required");
        }
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
