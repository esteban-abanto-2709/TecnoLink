package com.tecnolink.tecnolink.quotes.interfaces.rest.controllers;

import com.tecnolink.tecnolink.quotes.application.commands.RemoveComparisonProductCommand;
import com.tecnolink.tecnolink.quotes.application.dtos.ComparisonDto;
import com.tecnolink.tecnolink.quotes.application.dtos.ComparisonTableDto;
import com.tecnolink.tecnolink.quotes.application.handlers.commands.ComparisonCommandHandler;
import com.tecnolink.tecnolink.quotes.application.handlers.queries.GetComparisonQueryHandler;
import com.tecnolink.tecnolink.quotes.application.handlers.queries.GetComparisonTableQueryHandler;
import com.tecnolink.tecnolink.quotes.application.handlers.queries.ListClientComparisonsQueryHandler;
import com.tecnolink.tecnolink.quotes.application.queries.GetComparisonQuery;
import com.tecnolink.tecnolink.quotes.application.queries.GetComparisonTableQuery;
import com.tecnolink.tecnolink.quotes.application.queries.ListClientComparisonsQuery;
import com.tecnolink.tecnolink.quotes.interfaces.rest.documentation.ComparisonApi;
import com.tecnolink.tecnolink.quotes.interfaces.rest.mappers.ComparisonRestMapper;
import com.tecnolink.tecnolink.quotes.interfaces.rest.resources.ComparisonCreateRequest;
import com.tecnolink.tecnolink.quotes.interfaces.rest.resources.ComparisonProductRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comparisons")
public class ComparisonController implements ComparisonApi {
    private final ComparisonCommandHandler commands;
    private final GetComparisonQueryHandler get;
    private final ListClientComparisonsQueryHandler byClient;
    private final GetComparisonTableQueryHandler table;

    public ComparisonController(ComparisonCommandHandler commands, GetComparisonQueryHandler get, ListClientComparisonsQueryHandler byClient, GetComparisonTableQueryHandler table) {
        this.commands = commands;
        this.get = get;
        this.byClient = byClient;
        this.table = table;
    }

    @Override
    @PostMapping
    public ResponseEntity<ComparisonDto> create(@RequestBody ComparisonCreateRequest r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commands.handle(ComparisonRestMapper.toCommand(r)));
    }

    @Override
    @GetMapping("/{id}")
    public ComparisonDto get(@PathVariable Long id) {
        return get.handle(new GetComparisonQuery(id));
    }

    @Override
    @GetMapping(params = "clientId")
    public List<ComparisonDto> byClient(@RequestParam String clientId) {
        return byClient.handle(new ListClientComparisonsQuery(clientId));
    }

    @Override
    @PostMapping("/{id}/products")
    public ComparisonDto addProduct(@PathVariable Long id, @RequestBody ComparisonProductRequest r) {
        return commands.handle(ComparisonRestMapper.toCommand(id, r));
    }

    @Override
    @DeleteMapping("/{id}/products/{productId}")
    public ComparisonDto removeProduct(@PathVariable Long id, @PathVariable String productId) {
        return commands.handle(new RemoveComparisonProductCommand(id, productId));
    }

    @Override
    @GetMapping("/{id}/table")
    public ComparisonTableDto table(@PathVariable Long id) {
        return table.handle(new GetComparisonTableQuery(id));
    }
}
