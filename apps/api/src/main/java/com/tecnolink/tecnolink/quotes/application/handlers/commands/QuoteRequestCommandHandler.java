package com.tecnolink.tecnolink.quotes.application.handlers.commands;

import com.tecnolink.tecnolink.quotes.application.assemblers.QuotesDtoAssembler;
import com.tecnolink.tecnolink.quotes.application.commands.AnswerQuoteRequestCommand;
import com.tecnolink.tecnolink.quotes.application.commands.ExpireQuoteRequestCommand;
import com.tecnolink.tecnolink.quotes.application.commands.RequestQuoteCommand;
import com.tecnolink.tecnolink.quotes.application.dtos.QuoteRequestDto;
import com.tecnolink.tecnolink.quotes.domain.aggregates.QuoteRequest;
import com.tecnolink.tecnolink.quotes.domain.exceptions.QuoteRequestNotFoundException;
import com.tecnolink.tecnolink.quotes.domain.repositories.QuoteRequestRepository;
import com.tecnolink.tecnolink.shared.domain.events.DomainEventDispatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuoteRequestCommandHandler {
    private final QuoteRequestRepository repository;
    private final DomainEventDispatcher events;

    public QuoteRequestCommandHandler(QuoteRequestRepository repository, DomainEventDispatcher events) {
        this.repository = repository;
        this.events = events;
    }

    @Transactional
    public QuoteRequestDto handle(RequestQuoteCommand c) {
        QuoteRequest quoteRequest = new QuoteRequest(c.clientId(), c.listingId(), c.quantity(), c.requirement());
        QuoteRequest saved = repository.save(quoteRequest);
        events.dispatch(quoteRequest);
        return QuotesDtoAssembler.toDto(saved);
    }

    @Transactional
    public QuoteRequestDto handle(AnswerQuoteRequestCommand c) {
        QuoteRequest q = get(c.quoteRequestId());
        q.answer(c.validityDays(), c.subtotal(), c.conditions());
        return persist(q);
    }

    @Transactional
    public QuoteRequestDto handle(ExpireQuoteRequestCommand c) {
        QuoteRequest q = get(c.quoteRequestId());
        q.expire();
        return persist(q);
    }

    private QuoteRequest get(Long id) {
        return repository.findById(id).orElseThrow(() -> new QuoteRequestNotFoundException(id));
    }

    private QuoteRequestDto persist(QuoteRequest q) {
        QuoteRequest saved = repository.update(q);
        events.dispatch(q);
        return QuotesDtoAssembler.toDto(saved);
    }
}
