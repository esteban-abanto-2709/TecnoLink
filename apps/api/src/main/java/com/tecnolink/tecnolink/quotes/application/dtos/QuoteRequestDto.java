package com.tecnolink.tecnolink.quotes.application.dtos;

import com.tecnolink.tecnolink.quotes.domain.valueobjects.QuoteRequestStatus;

import java.time.LocalDate;

public record QuoteRequestDto(Long id,
                              String clientId,
                              String listingId,
                              LocalDate requestedOn,
                              int quantity,
                              String requirement,
                              QuoteRequestStatus status,
                              QuoteDto quote) {}
