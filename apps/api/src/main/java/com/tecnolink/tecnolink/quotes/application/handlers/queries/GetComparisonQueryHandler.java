package com.tecnolink.tecnolink.quotes.application.handlers.queries;

import com.tecnolink.tecnolink.quotes.application.assemblers.QuotesDtoAssembler;
import com.tecnolink.tecnolink.quotes.application.dtos.ComparisonDto;
import com.tecnolink.tecnolink.quotes.application.queries.GetComparisonQuery;
import com.tecnolink.tecnolink.quotes.domain.exceptions.ComparisonNotFoundException;
import com.tecnolink.tecnolink.quotes.domain.repositories.ComparisonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GetComparisonQueryHandler {
    private final ComparisonRepository comparisons;

    public GetComparisonQueryHandler(ComparisonRepository comparisons) {
        this.comparisons = comparisons;
    }

    public ComparisonDto handle(GetComparisonQuery q) {
        return QuotesDtoAssembler.toDto(comparisons.findById(q.id()).orElseThrow(() -> new ComparisonNotFoundException(q.id())));
    }
}
