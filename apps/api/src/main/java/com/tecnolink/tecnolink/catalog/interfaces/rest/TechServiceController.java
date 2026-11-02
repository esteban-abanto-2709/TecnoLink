package com.tecnolink.tecnolink.catalog.interfaces.rest;

import com.tecnolink.tecnolink.catalog.application.dtos.TechServiceDto;
import com.tecnolink.tecnolink.catalog.application.handlers.commands.TechServiceCommandHandler;
import com.tecnolink.tecnolink.catalog.application.handlers.queries.GetTechServiceQueryHandler;
import com.tecnolink.tecnolink.catalog.application.handlers.queries.GetTechServicesQueryHandler;
import com.tecnolink.tecnolink.catalog.application.queries.GetTechServiceQuery;
import com.tecnolink.tecnolink.catalog.application.queries.GetTechServicesQuery;
import com.tecnolink.tecnolink.catalog.interfaces.rest.dto.CreateTechServiceRequest;
import com.tecnolink.tecnolink.catalog.interfaces.rest.dto.UpdateTechServiceRequest;
import com.tecnolink.tecnolink.catalog.interfaces.rest.mappers.TechServiceRestMapper;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tech-services")
public class TechServiceController {

    private final TechServiceCommandHandler techServiceCommandHandler;
    private final GetTechServicesQueryHandler getTechServicesQueryHandler;
    private final GetTechServiceQueryHandler getTechServiceQueryHandler;

    public TechServiceController(
            TechServiceCommandHandler techServiceCommandHandler,
            GetTechServicesQueryHandler getTechServicesQueryHandler,
            GetTechServiceQueryHandler getTechServiceQueryHandler
    ) {
        this.techServiceCommandHandler = techServiceCommandHandler;
        this.getTechServicesQueryHandler = getTechServicesQueryHandler;
        this.getTechServiceQueryHandler = getTechServiceQueryHandler;
    }

    @GetMapping
    public List<TechServiceDto> getTechServices() {
        GetTechServicesQuery query = new GetTechServicesQuery();

        return getTechServicesQueryHandler.handle(query);
    }

    @GetMapping("/{id}")
    public TechServiceDto getTechService(@PathVariable String id) {
        GetTechServiceQuery query = new GetTechServiceQuery(id);

        return getTechServiceQueryHandler.handle(query);
    }

    @PostMapping
    public TechServiceDto createTechService(
            @Valid @RequestBody CreateTechServiceRequest request
    ) {
        return techServiceCommandHandler.handle(
                TechServiceRestMapper.toCommand(request)
        );
    }

    @PutMapping("/{id}")
    public TechServiceDto updateTechService(
            @PathVariable String id,
            @Valid @RequestBody UpdateTechServiceRequest request
    ) {
        return techServiceCommandHandler.handle(
                TechServiceRestMapper.toCommand(id, request)
        );
    }
}