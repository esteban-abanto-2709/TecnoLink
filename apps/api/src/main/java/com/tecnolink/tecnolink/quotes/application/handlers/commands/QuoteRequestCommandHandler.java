package com.tecnolink.tecnolink.quotes.application.handlers.commands;

import com.tecnolink.tecnolink.quotes.application.assemblers.QuotesDtoAssembler;
import com.tecnolink.tecnolink.quotes.application.commands.AnswerQuoteRequestCommand;
import com.tecnolink.tecnolink.quotes.application.commands.ExpireQuoteRequestCommand;
import com.tecnolink.tecnolink.quotes.application.commands.RequestQuoteCommand;
import com.tecnolink.tecnolink.quotes.application.dtos.QuoteRequestDto;
import com.tecnolink.tecnolink.quotes.application.ports.QuoteReferences;
import com.tecnolink.tecnolink.quotes.domain.aggregates.QuoteRequest;
import com.tecnolink.tecnolink.quotes.domain.exceptions.QuoteRequestNotFoundException;
import com.tecnolink.tecnolink.quotes.domain.repositories.QuoteRequestRepository;
import com.tecnolink.tecnolink.shared.domain.events.DomainEventDispatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class QuoteRequestCommandHandler {
    private final QuoteRequestRepository repository;
    private final QuoteReferences references;
    private final DomainEventDispatcher events;

    public QuoteRequestCommandHandler(QuoteRequestRepository repository, QuoteReferences references,
                                      DomainEventDispatcher events) {
        this.repository = repository;
        this.references = references;
        this.events = events;
    }

    @Transactional
    public QuoteRequestDto handle(RequestQuoteCommand c) {
        LocalDate today = LocalDate.now();
        QuoteRequest quoteRequest = new QuoteRequest(c.clientId(), c.listingId(), c.quantity(), c.requirement(), today);
        references.requireClient(quoteRequest.getClientId());
        references.requireAvailableListing(quoteRequest.getListingId());
        QuoteRequest saved = repository.save(quoteRequest);
        saved.announceRequested();
        events.dispatch(saved);
        return QuotesDtoAssembler.toDto(saved, today);
    }

    @Transactional
    public QuoteRequestDto handle(AnswerQuoteRequestCommand c) {
        LocalDate today = LocalDate.now();
        QuoteRequest q = get(c.quoteRequestId());
        q.answer(c.validityDays(), c.subtotal(), c.conditions(), c.notes(), today);
        return persist(q, today);
    }

    @Transactional
    public QuoteRequestDto handle(ExpireQuoteRequestCommand c) {
        LocalDate today = LocalDate.now();
        QuoteRequest q = get(c.quoteRequestId());
        q.expire(today);
        return persist(q, today);
    }

    private QuoteRequest get(Long id) {
        return repository.findById(id).orElseThrow(() -> new QuoteRequestNotFoundException(id));
    }

    private QuoteRequestDto persist(QuoteRequest q, LocalDate today) {
        QuoteRequest saved = repository.save(q);
        events.dispatch(q);
        return QuotesDtoAssembler.toDto(saved, today);
    }
}
