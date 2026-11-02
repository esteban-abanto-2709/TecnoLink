package com.tecnolink.tecnolink.catalog.application.handlers.queries;

import com.tecnolink.tecnolink.catalog.application.assemblers.TechServiceAssembler;
import com.tecnolink.tecnolink.catalog.application.dtos.TechServiceDto;
import com.tecnolink.tecnolink.catalog.application.queries.GetTechServiceQuery;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.TechService;
import com.tecnolink.tecnolink.catalog.domain.repositories.TechServiceRepository;
import org.springframework.stereotype.Service;

@Service
public class GetTechServiceQueryHandler {

    private final TechServiceRepository techServiceRepository;

    public GetTechServiceQueryHandler(
            TechServiceRepository techServiceRepository
    ) {
        this.techServiceRepository = techServiceRepository;
    }

    public TechServiceDto handle(GetTechServiceQuery query) {
        TechService service = techServiceRepository.findById(query.serviceId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Tech service not found")
                );

        return TechServiceAssembler.toDto(service);
    }
}