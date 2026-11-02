package com.tecnolink.tecnolink.quotes.domain.aggregates;

import com.tecnolink.tecnolink.quotes.domain.entities.Quote;
import com.tecnolink.tecnolink.quotes.domain.events.QuoteAnsweredEvent;
import com.tecnolink.tecnolink.quotes.domain.events.QuoteRequestedEvent;
import com.tecnolink.tecnolink.quotes.domain.valueobjects.QuoteRequestStatus;
import com.tecnolink.tecnolink.shared.domain.model.aggregates.AbstractDomainAggregateRoot;

import java.time.Instant;
import java.time.LocalDate;

public class QuoteRequest extends AbstractDomainAggregateRoot<QuoteRequest> {
    private Long id;
    private final String clientId;
    private final String listingId;
    private final LocalDate requestedOn;
    private final int quantity;
    private final String requirement;
    private QuoteRequestStatus status;
    private Quote quote;

    public QuoteRequest(String clientId, String listingId, int quantity, String requirement) {
        this(null, clientId, listingId, LocalDate.now(), quantity, requirement, QuoteRequestStatus.SENT, null);
        registerDomainEvent(new QuoteRequestedEvent(id, clientId, listingId, Instant.now()));
    }

    public QuoteRequest(Long id, String clientId, String listingId, LocalDate requestedOn, int quantity,
                        String requirement, QuoteRequestStatus status, Quote quote) {
        if (requestedOn == null) throw new IllegalArgumentException("Requested on is required");
        if (status == null) throw new IllegalArgumentException("Quote request status is required");
        if (quantity < 1) throw new IllegalArgumentException("Quantity must be at least 1");
        if (status != QuoteRequestStatus.SENT && quote == null)
            throw new IllegalArgumentException("An answered quote request needs a quote");
        this.id = id;
        this.clientId = required(clientId, "Client id");
        this.listingId = required(listingId, "Listing id");
        this.requestedOn = requestedOn;
        this.quantity = quantity;
        this.requirement = required(requirement, "Requirement");
        this.status = status;
        this.quote = quote;
    }

    public void answer(int validityDays, double subtotal, String conditions) {
        if (status != QuoteRequestStatus.SENT)
            throw new IllegalStateException("Only a sent quote request can be answered");
        quote = new Quote(LocalDate.now(), validityDays, subtotal, conditions);
        status = QuoteRequestStatus.ANSWERED;
        registerDomainEvent(new QuoteAnsweredEvent(id, clientId, quote.getTotal(), Instant.now()));
    }

    public void expire() {
        if (status != QuoteRequestStatus.ANSWERED)
            throw new IllegalStateException("Only an answered quote request can expire");
        if (quote.isValidOn(LocalDate.now()))
            throw new IllegalStateException("Quote is still valid until " + quote.validUntil());
        status = QuoteRequestStatus.EXPIRED;
    }

    private static String required(String value, String name) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(name + " is required");
        return value.trim();
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

    public LocalDate getRequestedOn() {
        return requestedOn;
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

    public Quote getQuote() {
        return quote;
    }
}
