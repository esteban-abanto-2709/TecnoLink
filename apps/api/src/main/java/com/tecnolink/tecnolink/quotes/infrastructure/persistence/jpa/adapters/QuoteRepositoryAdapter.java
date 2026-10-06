package com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.adapters;

import com.tecnolink.tecnolink.quotes.domain.model.aggregates.Quote;
import com.tecnolink.tecnolink.quotes.domain.repositories.QuoteRepository;
import com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.mappers.QuotePersistenceMapper;
import com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.repositories.QuoteJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class QuoteRepositoryAdapter implements QuoteRepository {

    private final QuoteJpaRepository jpaRepository;

    public QuoteRepositoryAdapter(QuoteJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Quote> findById(Long id) {
        return jpaRepository.findById(id).map(QuotePersistenceMapper::toDomain);
    }

    @Override
    public Quote save(Quote quote) {
        return QuotePersistenceMapper.toDomain(jpaRepository.save(QuotePersistenceMapper.toEntity(quote)));
    }
}
