package com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.adapters;

import com.tecnolink.tecnolink.quotes.domain.aggregates.QuoteRequest;
import com.tecnolink.tecnolink.quotes.domain.repositories.QuoteRequestRepository;
import com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.mappers.QuoteRequestPersistenceMapper;
import com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.repositories.QuoteRequestJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class QuoteRequestRepositoryAdapter implements QuoteRequestRepository {
    private final QuoteRequestJpaRepository repo;
    private final QuoteRequestPersistenceMapper mapper;

    public QuoteRequestRepositoryAdapter(QuoteRequestJpaRepository repo, QuoteRequestPersistenceMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public QuoteRequest save(QuoteRequest quoteRequest) {
        return mapper.toDomain(repo.save(mapper.toEntity(quoteRequest)));
    }

    @Override
    public Optional<QuoteRequest> findById(Long id) {
        return repo.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<QuoteRequest> findByClientId(String clientId) {
        return repo.findByClientId(clientId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<QuoteRequest> findBySupplierId(String supplierId) {
        return repo.findByListing_SupplierId(supplierId).stream().map(mapper::toDomain).toList();
    }
}
