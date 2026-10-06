package com.tecnolink.tecnolink.quotes.domain.repositories;

import com.tecnolink.tecnolink.quotes.domain.model.aggregates.QuoteRequest;

import java.util.Optional;

public interface QuoteRequestRepository {
    Optional<QuoteRequest> findById(Long id);
    QuoteRequest save(QuoteRequest request);
}
