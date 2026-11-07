package com.tecnolink.tecnolink.quotes.application.handlers.queries;

import com.tecnolink.tecnolink.quotes.application.assemblers.QuotesDtoAssembler;
import com.tecnolink.tecnolink.quotes.application.dtos.QuoteRequestDto;
import com.tecnolink.tecnolink.quotes.application.queries.ListSupplierQuoteRequestsQuery;
import com.tecnolink.tecnolink.quotes.domain.repositories.QuoteRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ListSupplierQuoteRequestsQueryHandler {
    private final QuoteRequestRepository quoteRequests;

    public ListSupplierQuoteRequestsQueryHandler(QuoteRequestRepository quoteRequests) {
        this.quoteRequests = quoteRequests;
    }

    public List<QuoteRequestDto> handle(ListSupplierQuoteRequestsQuery q) {
        return quoteRequests.findBySupplierId(q.supplierId()).stream().map(QuotesDtoAssembler::toDto).toList();
    }
}
