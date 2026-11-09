package com.tecnolink.tecnolink.quotes.application.handlers.queries;

import com.tecnolink.tecnolink.quotes.application.assemblers.QuotesDtoAssembler;
import com.tecnolink.tecnolink.quotes.application.dtos.ComparisonDto;
import com.tecnolink.tecnolink.quotes.application.dtos.ComparisonTableDto;
import com.tecnolink.tecnolink.quotes.application.queries.GetComparisonQuery;
import com.tecnolink.tecnolink.quotes.application.queries.GetComparisonTableQuery;
import com.tecnolink.tecnolink.quotes.application.queries.ListClientComparisonsQuery;
import com.tecnolink.tecnolink.quotes.domain.aggregates.Comparison;
import com.tecnolink.tecnolink.quotes.domain.exceptions.ComparisonNotFoundException;
import com.tecnolink.tecnolink.quotes.domain.repositories.ComparisonRepository;
import com.tecnolink.tecnolink.quotes.domain.repositories.SpecificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ComparisonQueryHandler {
    private final ComparisonRepository comparisons;
    private final SpecificationRepository specifications;

    public ComparisonQueryHandler(ComparisonRepository comparisons, SpecificationRepository specifications) {
        this.comparisons = comparisons;
        this.specifications = specifications;
    }

    public ComparisonDto handle(GetComparisonQuery q) {
        return QuotesDtoAssembler.toDto(find(q.id()));
    }

    public List<ComparisonDto> handle(ListClientComparisonsQuery q) {
        return comparisons.findByClientId(q.clientId()).stream().map(QuotesDtoAssembler::toDto).toList();
    }

    public ComparisonTableDto handle(GetComparisonTableQuery q) {
        Comparison comparison = find(q.comparisonId());
        return QuotesDtoAssembler.toTableDto(comparison, comparison.buildTable(specifications.findByProductIdIn(comparison.getProductIds())));
    }

    private Comparison find(Long id) {
        return comparisons.findById(id).orElseThrow(() -> new ComparisonNotFoundException(id));
    }
}
