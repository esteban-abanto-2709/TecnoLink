package com.tecnolink.tecnolink.quotes.interfaces.rest.controllers;

import com.tecnolink.tecnolink.quotes.application.dtos.QuoteRequestDto;
import com.tecnolink.tecnolink.quotes.application.handlers.commands.QuoteRequestCommandHandler;
import com.tecnolink.tecnolink.quotes.application.handlers.queries.GetQuoteRequestQueryHandler;
import com.tecnolink.tecnolink.quotes.application.handlers.queries.ListClientQuoteRequestsQueryHandler;
import com.tecnolink.tecnolink.quotes.application.handlers.queries.ListSupplierQuoteRequestsQueryHandler;
import com.tecnolink.tecnolink.quotes.application.queries.GetQuoteRequestQuery;
import com.tecnolink.tecnolink.quotes.application.queries.ListClientQuoteRequestsQuery;
import com.tecnolink.tecnolink.quotes.application.queries.ListSupplierQuoteRequestsQuery;
import com.tecnolink.tecnolink.quotes.interfaces.rest.documentation.QuoteRequestApi;
import com.tecnolink.tecnolink.quotes.interfaces.rest.mappers.QuoteRequestRestMapper;
import com.tecnolink.tecnolink.quotes.interfaces.rest.resources.QuoteRequestCreateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quote-requests")
public class QuoteRequestController implements QuoteRequestApi {
    private final QuoteRequestCommandHandler commands;
    private final GetQuoteRequestQueryHandler get;
    private final ListClientQuoteRequestsQueryHandler byClient;
    private final ListSupplierQuoteRequestsQueryHandler bySupplier;

    public QuoteRequestController(QuoteRequestCommandHandler commands, GetQuoteRequestQueryHandler get, ListClientQuoteRequestsQueryHandler byClient, ListSupplierQuoteRequestsQueryHandler bySupplier) {
        this.commands = commands;
        this.get = get;
        this.byClient = byClient;
        this.bySupplier = bySupplier;
    }

    @Override
    @PostMapping
    public ResponseEntity<QuoteRequestDto> request(@RequestBody QuoteRequestCreateRequest r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commands.handle(QuoteRequestRestMapper.toCommand(r)));
    }

    @Override
    @GetMapping("/{id}")
    public QuoteRequestDto get(@PathVariable Long id) {
        return get.handle(new GetQuoteRequestQuery(id));
    }

    @Override
    @GetMapping(params = "clientId")
    public List<QuoteRequestDto> byClient(@RequestParam String clientId) {
        return byClient.handle(new ListClientQuoteRequestsQuery(clientId));
    }

    @Override
    @GetMapping(params = "supplierId")
    public List<QuoteRequestDto> bySupplier(@RequestParam String supplierId) {
        return bySupplier.handle(new ListSupplierQuoteRequestsQuery(supplierId));
    }
}
