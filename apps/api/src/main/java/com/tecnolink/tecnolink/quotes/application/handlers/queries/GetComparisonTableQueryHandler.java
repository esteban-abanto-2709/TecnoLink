package com.tecnolink.tecnolink.quotes.application.handlers.queries;

import com.tecnolink.tecnolink.quotes.application.assemblers.QuotesDtoAssembler;
import com.tecnolink.tecnolink.quotes.application.dtos.ComparisonTableDto;
import com.tecnolink.tecnolink.quotes.application.queries.GetComparisonTableQuery;
import com.tecnolink.tecnolink.quotes.domain.aggregates.Comparison;
import com.tecnolink.tecnolink.quotes.domain.exceptions.ComparisonNotFoundException;
import com.tecnolink.tecnolink.quotes.domain.repositories.ComparisonRepository;
import com.tecnolink.tecnolink.quotes.domain.repositories.SpecificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GetComparisonTableQueryHandler {
    private final ComparisonRepository comparisons;
    private final SpecificationRepository specifications;

    public GetComparisonTableQueryHandler(ComparisonRepository comparisons, SpecificationRepository specifications) {
        this.comparisons = comparisons;
        this.specifications = specifications;
    }

    public ComparisonTableDto handle(GetComparisonTableQuery q) {
        Comparison comparison = comparisons.findById(q.comparisonId()).orElseThrow(() -> new ComparisonNotFoundException(q.comparisonId()));
        return QuotesDtoAssembler.toTableDto(comparison, comparison.buildTable(specifications.findByProductIdIn(comparison.getProductIds())));
    }
}
