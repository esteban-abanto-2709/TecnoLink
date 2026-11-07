package com.tecnolink.tecnolink.quotes.interfaces.rest.resources;

public record QuoteRequestAnswerRequest(int validityDays, double subtotal, String conditions) {}
