package com.tecnolink.tecnolink.quotes.application.handlers.queries;

import com.tecnolink.tecnolink.quotes.application.assemblers.QuotesDtoAssembler;
import com.tecnolink.tecnolink.quotes.application.dtos.QuoteRequestDto;
import com.tecnolink.tecnolink.quotes.application.queries.GetQuoteRequestQuery;
import com.tecnolink.tecnolink.quotes.application.queries.ListClientQuoteRequestsQuery;
import com.tecnolink.tecnolink.quotes.application.queries.ListSupplierQuoteRequestsQuery;
import com.tecnolink.tecnolink.quotes.domain.aggregates.QuoteRequest;
import com.tecnolink.tecnolink.quotes.domain.exceptions.QuoteRequestNotFoundException;
import com.tecnolink.tecnolink.quotes.domain.repositories.QuoteRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class QuoteRequestQueryHandler {
    private final QuoteRequestRepository quoteRequests;

    public QuoteRequestQueryHandler(QuoteRequestRepository quoteRequests) {
        this.quoteRequests = quoteRequests;
    }

    public QuoteRequestDto handle(GetQuoteRequestQuery q) {
        QuoteRequest quoteRequest = quoteRequests.findById(q.id()).orElseThrow(() -> new QuoteRequestNotFoundException(q.id()));
        return QuotesDtoAssembler.toDto(quoteRequest, LocalDate.now());
    }

    public List<QuoteRequestDto> handle(ListClientQuoteRequestsQuery q) {
        return toDtos(quoteRequests.findByClientId(q.clientId()));
    }

    public List<QuoteRequestDto> handle(ListSupplierQuoteRequestsQuery q) {
        return toDtos(quoteRequests.findBySupplierId(q.supplierId()));
    }

    private List<QuoteRequestDto> toDtos(List<QuoteRequest> found) {
        LocalDate today = LocalDate.now();
        return found.stream().map(quoteRequest -> QuotesDtoAssembler.toDto(quoteRequest, today)).toList();
    }
}
