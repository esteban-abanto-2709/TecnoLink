package com.tecnolink.tecnolink.quotes.interfaces.rest.controllers;

import com.tecnolink.tecnolink.quotes.application.dtos.SpecificationDto;
import com.tecnolink.tecnolink.quotes.application.handlers.commands.SpecificationCommandHandler;
import com.tecnolink.tecnolink.quotes.application.handlers.queries.SpecificationQueryHandler;
import com.tecnolink.tecnolink.quotes.application.queries.GetSpecificationQuery;
import com.tecnolink.tecnolink.quotes.application.queries.ListProductSpecificationsQuery;
import com.tecnolink.tecnolink.quotes.interfaces.rest.documentation.SpecificationApi;
import com.tecnolink.tecnolink.quotes.interfaces.rest.mappers.SpecificationRestMapper;
import com.tecnolink.tecnolink.quotes.interfaces.rest.resources.SpecificationCreateRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/specifications")
public class SpecificationController implements SpecificationApi {
    private final SpecificationCommandHandler commands;
    private final SpecificationQueryHandler queries;

    public SpecificationController(SpecificationCommandHandler commands, SpecificationQueryHandler queries) {
        this.commands = commands;
        this.queries = queries;
    }

    @Override
    @PostMapping
    public ResponseEntity<SpecificationDto> add(@Valid @RequestBody SpecificationCreateRequest r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commands.handle(SpecificationRestMapper.toCommand(r)));
    }

    @Override
    @GetMapping("/{id}")
    public SpecificationDto get(@PathVariable Long id) {
        return queries.handle(new GetSpecificationQuery(id));
    }

    @Override
    @GetMapping(params = "productId")
    public List<SpecificationDto> byProduct(@RequestParam String productId) {
        return queries.handle(new ListProductSpecificationsQuery(productId));
    }
}
