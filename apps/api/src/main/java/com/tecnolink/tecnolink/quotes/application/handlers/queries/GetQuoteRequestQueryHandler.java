package com.tecnolink.tecnolink.quotes.application.handlers.queries;

import com.tecnolink.tecnolink.quotes.application.assemblers.QuotesDtoAssembler;
import com.tecnolink.tecnolink.quotes.application.dtos.QuoteRequestDto;
import com.tecnolink.tecnolink.quotes.application.queries.GetQuoteRequestQuery;
import com.tecnolink.tecnolink.quotes.domain.exceptions.QuoteRequestNotFoundException;
import com.tecnolink.tecnolink.quotes.domain.repositories.QuoteRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GetQuoteRequestQueryHandler {
    private final QuoteRequestRepository quoteRequests;

    public GetQuoteRequestQueryHandler(QuoteRequestRepository quoteRequests) {
        this.quoteRequests = quoteRequests;
    }

    public QuoteRequestDto handle(GetQuoteRequestQuery q) {
        return QuotesDtoAssembler.toDto(quoteRequests.findById(q.id()).orElseThrow(() -> new QuoteRequestNotFoundException(q.id())));
    }
}
