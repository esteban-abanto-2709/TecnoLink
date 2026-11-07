package com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.adapters;

import com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.repositories.ProductJpaRepository;
import com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.repositories.TechServiceJpaRepository;
import com.tecnolink.tecnolink.iam.infrastructure.persistence.jpa.repositories.ClientJpaRepository;
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
    private final ClientJpaRepository clientRepo;
    private final ProductJpaRepository productRepo;
    private final TechServiceJpaRepository techServiceRepo;

    public QuoteRequestRepositoryAdapter(
            QuoteRequestJpaRepository repo,
            QuoteRequestPersistenceMapper mapper,
            ClientJpaRepository clientRepo,
            ProductJpaRepository productRepo,
            TechServiceJpaRepository techServiceRepo) {
        this.repo = repo;
        this.mapper = mapper;
        this.clientRepo = clientRepo;
        this.productRepo = productRepo;
        this.techServiceRepo = techServiceRepo;
    }

    @Override
    public QuoteRequest save(QuoteRequest quoteRequest) {
        if (!clientRepo.existsById(quoteRequest.getClientId()))
            throw new IllegalArgumentException("Client not found: " + quoteRequest.getClientId());
        if (!productRepo.existsById(quoteRequest.getListingId()) && !techServiceRepo.existsById(quoteRequest.getListingId()))
            throw new IllegalArgumentException("Listing not found: " + quoteRequest.getListingId());
        return mapper.toDomain(repo.save(mapper.toEntity(quoteRequest)));
    }

    @Override
    public QuoteRequest update(QuoteRequest quoteRequest) {
        return save(quoteRequest);
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
