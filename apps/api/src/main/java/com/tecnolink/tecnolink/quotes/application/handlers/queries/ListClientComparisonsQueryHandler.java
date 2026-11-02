package com.tecnolink.tecnolink.quotes.application.handlers.queries;

import com.tecnolink.tecnolink.quotes.application.assemblers.QuotesDtoAssembler;
import com.tecnolink.tecnolink.quotes.application.dtos.ComparisonDto;
import com.tecnolink.tecnolink.quotes.application.queries.ListClientComparisonsQuery;
import com.tecnolink.tecnolink.quotes.domain.repositories.ComparisonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ListClientComparisonsQueryHandler {
    private final ComparisonRepository comparisons;

    public ListClientComparisonsQueryHandler(ComparisonRepository comparisons) {
        this.comparisons = comparisons;
    }

    public List<ComparisonDto> handle(ListClientComparisonsQuery q) {
        return comparisons.findByClientId(q.clientId()).stream().map(QuotesDtoAssembler::toDto).toList();
    }
}
