package com.tecnolink.tecnolink.catalog.application.handlers.queries;

import com.tecnolink.tecnolink.catalog.application.assemblers.TechServiceAssembler;
import com.tecnolink.tecnolink.catalog.application.dtos.TechServiceDto;
import com.tecnolink.tecnolink.catalog.application.queries.GetTechServicesQuery;
import com.tecnolink.tecnolink.catalog.domain.repositories.TechServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTechServicesQueryHandler {

    private final TechServiceRepository techServiceRepository;

    public GetTechServicesQueryHandler(
            TechServiceRepository techServiceRepository
    ) {
        this.techServiceRepository = techServiceRepository;
    }

    public List<TechServiceDto> handle(GetTechServicesQuery query) {
        return techServiceRepository.findAll()
                .stream()
                .map(TechServiceAssembler::toDto)
                .toList();
    }
}