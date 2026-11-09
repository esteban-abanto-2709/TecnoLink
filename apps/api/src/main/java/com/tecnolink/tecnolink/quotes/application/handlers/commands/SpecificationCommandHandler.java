package com.tecnolink.tecnolink.quotes.application.handlers.commands;

import com.tecnolink.tecnolink.quotes.application.assemblers.QuotesDtoAssembler;
import com.tecnolink.tecnolink.quotes.application.commands.AddSpecificationCommand;
import com.tecnolink.tecnolink.quotes.application.dtos.SpecificationDto;
import com.tecnolink.tecnolink.quotes.application.ports.QuoteReferences;
import com.tecnolink.tecnolink.quotes.domain.aggregates.Specification;
import com.tecnolink.tecnolink.quotes.domain.repositories.SpecificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SpecificationCommandHandler {
    private final SpecificationRepository repository;
    private final QuoteReferences references;

    public SpecificationCommandHandler(SpecificationRepository repository, QuoteReferences references) {
        this.repository = repository;
        this.references = references;
    }

    @Transactional
    public SpecificationDto handle(AddSpecificationCommand c) {
        Specification specification = new Specification(c.productId(), c.name(), c.value(), c.unit());
        references.requireProduct(specification.getProductId());
        if (repository.existsByProductIdAndName(specification.getProductId(), specification.getName()))
            throw new IllegalStateException("Product already has a specification named " + specification.getName());
        return QuotesDtoAssembler.toDto(repository.save(specification));
    }
}
