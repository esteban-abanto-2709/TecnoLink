package com.tecnolink.tecnolink.quotes.application.dtos;

import java.time.LocalDate;

public record QuoteDto(Long id,
                       LocalDate issueDate,
                       int validityDays,
                       LocalDate validUntil,
                       double subtotal,
                       double igv,
                       double total,
                       String conditions) {}
