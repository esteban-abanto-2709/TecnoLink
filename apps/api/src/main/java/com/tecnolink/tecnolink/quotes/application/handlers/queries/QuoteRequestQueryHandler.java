package com.tecnolink.tecnolink.quotes.application.handlers.queries;

import com.tecnolink.tecnolink.quotes.application.assemblers.QuotesDtoAssembler;
import com.tecnolink.tecnolink.quotes.application.dtos.QuoteRequestDto;
import com.tecnolink.tecnolink.quotes.application.queries.GetQuoteRequestQuery;
import com.tecnolink.tecnolink.quotes.application.queries.ListClientQuoteRequestsQuery;
import com.tecnolink.tecnolink.quotes.application.queries.ListSupplierQuoteRequestsQuery;
import com.tecnolink.tecnolink.quotes.domain.exceptions.QuoteRequestNotFoundException;
import com.tecnolink.tecnolink.quotes.domain.repositories.QuoteRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class QuoteRequestQueryHandler {
    private final QuoteRequestRepository quoteRequests;

    public QuoteRequestQueryHandler(QuoteRequestRepository quoteRequests) {
        this.quoteRequests = quoteRequests;
    }

    public QuoteRequestDto handle(GetQuoteRequestQuery q) {
        return QuotesDtoAssembler.toDto(quoteRequests.findById(q.id()).orElseThrow(() -> new QuoteRequestNotFoundException(q.id())));
    }

    public List<QuoteRequestDto> handle(ListClientQuoteRequestsQuery q) {
        return quoteRequests.findByClientId(q.clientId()).stream().map(QuotesDtoAssembler::toDto).toList();
    }

    public List<QuoteRequestDto> handle(ListSupplierQuoteRequestsQuery q) {
        return quoteRequests.findBySupplierId(q.supplierId()).stream().map(QuotesDtoAssembler::toDto).toList();
    }
}
