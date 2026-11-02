package com.tecnolink.tecnolink.quotes.application.handlers.queries;

import com.tecnolink.tecnolink.quotes.application.assemblers.QuotesDtoAssembler;
import com.tecnolink.tecnolink.quotes.application.dtos.QuoteRequestDto;
import com.tecnolink.tecnolink.quotes.application.queries.ListClientQuoteRequestsQuery;
import com.tecnolink.tecnolink.quotes.domain.repositories.QuoteRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ListClientQuoteRequestsQueryHandler {
    private final QuoteRequestRepository quoteRequests;

    public ListClientQuoteRequestsQueryHandler(QuoteRequestRepository quoteRequests) {
        this.quoteRequests = quoteRequests;
    }

    public List<QuoteRequestDto> handle(ListClientQuoteRequestsQuery q) {
        return quoteRequests.findByClientId(q.clientId()).stream().map(QuotesDtoAssembler::toDto).toList();
    }
}
