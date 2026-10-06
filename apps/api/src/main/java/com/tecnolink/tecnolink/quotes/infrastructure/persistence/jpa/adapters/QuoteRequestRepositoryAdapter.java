package com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.adapters;

import com.tecnolink.tecnolink.quotes.domain.model.aggregates.QuoteRequest;
import com.tecnolink.tecnolink.quotes.domain.repositories.QuoteRequestRepository;
import com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.mappers.QuoteRequestPersistenceMapper;
import com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.repositories.QuoteRequestJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class QuoteRequestRepositoryAdapter implements QuoteRequestRepository {

    private final QuoteRequestJpaRepository jpaRepository;

    public QuoteRequestRepositoryAdapter(QuoteRequestJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<QuoteRequest> findById(Long id) {
        return jpaRepository.findById(id).map(QuoteRequestPersistenceMapper::toDomain);
    }

    @Override
    public QuoteRequest save(QuoteRequest request) {
        return QuoteRequestPersistenceMapper.toDomain(jpaRepository.save(QuoteRequestPersistenceMapper.toEntity(request)));
    }
}
