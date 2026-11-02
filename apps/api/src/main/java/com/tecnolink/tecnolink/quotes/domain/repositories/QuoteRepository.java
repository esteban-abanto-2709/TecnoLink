package com.tecnolink.tecnolink.quotes.domain.repositories;

import com.tecnolink.tecnolink.quotes.domain.aggregates.Quote;

import java.util.Optional;

public interface QuoteRepository {
    Optional<Quote> findById(Long id);
    Quote save(Quote quote);
}
