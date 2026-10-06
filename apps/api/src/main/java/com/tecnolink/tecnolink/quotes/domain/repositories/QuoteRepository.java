package com.tecnolink.tecnolink.quotes.domain.repositories;

import com.tecnolink.tecnolink.quotes.domain.model.aggregates.Quote;

import java.util.Optional;

public interface QuoteRepository {
    Optional<Quote> findById(Long id);
    Quote save(Quote quote);
}
