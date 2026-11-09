package com.tecnolink.tecnolink.quotes.interfaces.rest.controllers;

import com.tecnolink.tecnolink.quotes.application.commands.ExpireQuoteRequestCommand;
import com.tecnolink.tecnolink.quotes.application.dtos.QuoteRequestDto;
import com.tecnolink.tecnolink.quotes.application.handlers.commands.QuoteRequestCommandHandler;
import com.tecnolink.tecnolink.quotes.application.handlers.queries.QuoteRequestQueryHandler;
import com.tecnolink.tecnolink.quotes.application.queries.GetQuoteRequestQuery;
import com.tecnolink.tecnolink.quotes.application.queries.ListClientQuoteRequestsQuery;
import com.tecnolink.tecnolink.quotes.application.queries.ListSupplierQuoteRequestsQuery;
import com.tecnolink.tecnolink.quotes.interfaces.rest.documentation.QuoteRequestApi;
import com.tecnolink.tecnolink.quotes.interfaces.rest.mappers.QuoteRequestRestMapper;
import com.tecnolink.tecnolink.quotes.interfaces.rest.resources.QuoteRequestAnswerRequest;
import com.tecnolink.tecnolink.quotes.interfaces.rest.resources.QuoteRequestCreateRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quote-requests")
public class QuoteRequestController implements QuoteRequestApi {
    private final QuoteRequestCommandHandler commands;
    private final QuoteRequestQueryHandler queries;

    public QuoteRequestController(QuoteRequestCommandHandler commands, QuoteRequestQueryHandler queries) {
        this.commands = commands;
        this.queries = queries;
    }

    @Override
    @PostMapping
    public ResponseEntity<QuoteRequestDto> request(@Valid @RequestBody QuoteRequestCreateRequest r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commands.handle(QuoteRequestRestMapper.toCommand(r)));
    }

    @Override
    @GetMapping("/{id}")
    public QuoteRequestDto get(@PathVariable Long id) {
        return queries.handle(new GetQuoteRequestQuery(id));
    }

    @Override
    @GetMapping(params = "clientId")
    public List<QuoteRequestDto> byClient(@RequestParam String clientId) {
        return queries.handle(new ListClientQuoteRequestsQuery(clientId));
    }

    @Override
    @GetMapping(params = {"supplierId", "!clientId"})
    public List<QuoteRequestDto> bySupplier(@RequestParam String supplierId) {
        return queries.handle(new ListSupplierQuoteRequestsQuery(supplierId));
    }

    @Override
    @PostMapping("/{id}/answer")
    public QuoteRequestDto answer(@PathVariable Long id, @Valid @RequestBody QuoteRequestAnswerRequest r) {
        return commands.handle(QuoteRequestRestMapper.toCommand(id, r));
    }

    @Override
    @PostMapping("/{id}/expire")
    public QuoteRequestDto expire(@PathVariable Long id) {
        return commands.handle(new ExpireQuoteRequestCommand(id));
    }
}
