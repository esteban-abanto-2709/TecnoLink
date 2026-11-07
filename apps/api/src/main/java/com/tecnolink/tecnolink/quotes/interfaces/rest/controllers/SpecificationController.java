package com.tecnolink.tecnolink.quotes.interfaces.rest.controllers;

import com.tecnolink.tecnolink.quotes.application.dtos.SpecificationDto;
import com.tecnolink.tecnolink.quotes.application.handlers.commands.SpecificationCommandHandler;
import com.tecnolink.tecnolink.quotes.application.handlers.queries.GetSpecificationQueryHandler;
import com.tecnolink.tecnolink.quotes.application.handlers.queries.ListProductSpecificationsQueryHandler;
import com.tecnolink.tecnolink.quotes.application.queries.GetSpecificationQuery;
import com.tecnolink.tecnolink.quotes.application.queries.ListProductSpecificationsQuery;
import com.tecnolink.tecnolink.quotes.interfaces.rest.documentation.SpecificationApi;
import com.tecnolink.tecnolink.quotes.interfaces.rest.mappers.SpecificationRestMapper;
import com.tecnolink.tecnolink.quotes.interfaces.rest.resources.SpecificationCreateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/specifications")
public class SpecificationController implements SpecificationApi {
    private final SpecificationCommandHandler commands;
    private final GetSpecificationQueryHandler get;
    private final ListProductSpecificationsQueryHandler byProduct;

    public SpecificationController(SpecificationCommandHandler commands, GetSpecificationQueryHandler get, ListProductSpecificationsQueryHandler byProduct) {
        this.commands = commands;
        this.get = get;
        this.byProduct = byProduct;
    }

    @Override
    @PostMapping
    public ResponseEntity<SpecificationDto> add(@RequestBody SpecificationCreateRequest r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commands.handle(SpecificationRestMapper.toCommand(r)));
    }

    @Override
    @GetMapping("/{id}")
    public SpecificationDto get(@PathVariable Long id) {
        return get.handle(new GetSpecificationQuery(id));
    }

    @Override
    @GetMapping(params = "productId")
    public List<SpecificationDto> byProduct(@RequestParam String productId) {
        return byProduct.handle(new ListProductSpecificationsQuery(productId));
    }
}
