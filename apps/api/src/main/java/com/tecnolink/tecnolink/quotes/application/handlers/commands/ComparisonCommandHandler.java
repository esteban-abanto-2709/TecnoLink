package com.tecnolink.tecnolink.quotes.application.handlers.commands;

import com.tecnolink.tecnolink.quotes.application.assemblers.QuotesDtoAssembler;
import com.tecnolink.tecnolink.quotes.application.commands.AddComparisonProductCommand;
import com.tecnolink.tecnolink.quotes.application.commands.CreateComparisonCommand;
import com.tecnolink.tecnolink.quotes.application.commands.RemoveComparisonProductCommand;
import com.tecnolink.tecnolink.quotes.application.dtos.ComparisonDto;
import com.tecnolink.tecnolink.quotes.domain.aggregates.Comparison;
import com.tecnolink.tecnolink.quotes.domain.exceptions.ComparisonNotFoundException;
import com.tecnolink.tecnolink.quotes.domain.repositories.ComparisonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComparisonCommandHandler {
    private final ComparisonRepository repository;

    public ComparisonCommandHandler(ComparisonRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ComparisonDto handle(CreateComparisonCommand c) {
        Comparison comparison = new Comparison(c.clientId(), c.productIds());
        return QuotesDtoAssembler.toDto(repository.save(comparison));
    }

    @Transactional
    public ComparisonDto handle(AddComparisonProductCommand c) {
        Comparison comparison = get(c.comparisonId());
        comparison.addProduct(c.productId());
        return QuotesDtoAssembler.toDto(repository.update(comparison));
    }

    @Transactional
    public ComparisonDto handle(RemoveComparisonProductCommand c) {
        Comparison comparison = get(c.comparisonId());
        comparison.removeProduct(c.productId());
        return QuotesDtoAssembler.toDto(repository.update(comparison));
    }

    private Comparison get(Long id) {
        return repository.findById(id).orElseThrow(() -> new ComparisonNotFoundException(id));
    }
}
