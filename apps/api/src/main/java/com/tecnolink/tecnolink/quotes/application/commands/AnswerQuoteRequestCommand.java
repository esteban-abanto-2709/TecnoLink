package com.tecnolink.tecnolink.quotes.application.commands;
public record AnswerQuoteRequestCommand(Long quoteRequestId, int validityDays, double subtotal, String conditions) {}
