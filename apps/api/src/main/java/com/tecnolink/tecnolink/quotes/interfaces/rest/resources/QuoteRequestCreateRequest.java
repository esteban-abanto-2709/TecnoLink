package com.tecnolink.tecnolink.quotes.interfaces.rest.resources;

public record QuoteRequestCreateRequest(String clientId, String listingId, int quantity, String requirement) {}
