package com.tecnolink.tecnolink.quotes.domain.repositories;

import com.tecnolink.tecnolink.quotes.domain.aggregates.QuoteRequest;

import java.util.List;
import java.util.Optional;

public interface QuoteRequestRepository {
    QuoteRequest save(QuoteRequest quoteRequest);
    QuoteRequest update(QuoteRequest quoteRequest);
    Optional<QuoteRequest> findById(Long id);
    List<QuoteRequest> findByClientId(String clientId);
    List<QuoteRequest> findBySupplierId(String supplierId);
}
