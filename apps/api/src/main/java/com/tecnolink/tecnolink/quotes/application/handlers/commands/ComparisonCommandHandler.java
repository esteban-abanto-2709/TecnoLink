package com.tecnolink.tecnolink.quotes.application.handlers.commands;

import com.tecnolink.tecnolink.quotes.application.assemblers.QuotesDtoAssembler;
import com.tecnolink.tecnolink.quotes.application.commands.AddComparisonProductCommand;
import com.tecnolink.tecnolink.quotes.application.commands.CreateComparisonCommand;
import com.tecnolink.tecnolink.quotes.application.commands.RemoveComparisonProductCommand;
import com.tecnolink.tecnolink.quotes.application.dtos.ComparisonDto;
import com.tecnolink.tecnolink.quotes.application.ports.QuoteReferences;
import com.tecnolink.tecnolink.quotes.domain.aggregates.Comparison;
import com.tecnolink.tecnolink.quotes.domain.exceptions.ComparisonNotFoundException;
import com.tecnolink.tecnolink.quotes.domain.repositories.ComparisonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ComparisonCommandHandler {
    private final ComparisonRepository repository;
    private final QuoteReferences references;

    public ComparisonCommandHandler(ComparisonRepository repository, QuoteReferences references) {
        this.repository = repository;
        this.references = references;
    }

    @Transactional
    public ComparisonDto handle(CreateComparisonCommand c) {
        Comparison comparison = new Comparison(c.clientId(), c.productIds(), LocalDate.now());
        references.requireClient(comparison.getClientId());
        comparison.getProductIds().forEach(references::requireAvailableProduct);
        return QuotesDtoAssembler.toDto(repository.save(comparison));
    }

    @Transactional
    public ComparisonDto handle(AddComparisonProductCommand c) {
        Comparison comparison = get(c.comparisonId());
        comparison.addProduct(c.productId());
        references.requireAvailableProduct(c.productId());
        return QuotesDtoAssembler.toDto(repository.save(comparison));
    }

    @Transactional
    public ComparisonDto handle(RemoveComparisonProductCommand c) {
        Comparison comparison = get(c.comparisonId());
        comparison.removeProduct(c.productId());
        return QuotesDtoAssembler.toDto(repository.save(comparison));
    }

    private Comparison get(Long id) {
        return repository.findById(id).orElseThrow(() -> new ComparisonNotFoundException(id));
    }
}
