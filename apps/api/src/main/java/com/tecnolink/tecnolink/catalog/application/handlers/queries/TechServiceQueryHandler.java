package com.tecnolink.tecnolink.catalog.application.handlers.queries;

import com.tecnolink.tecnolink.catalog.application.assemblers.TechServiceAssembler;
import com.tecnolink.tecnolink.catalog.application.dtos.TechServiceDto;
import com.tecnolink.tecnolink.catalog.application.queries.GetTechServiceQuery;
import com.tecnolink.tecnolink.catalog.application.queries.GetTechServicesQuery;
import com.tecnolink.tecnolink.catalog.domain.repositories.TechServiceRepository;
import com.tecnolink.tecnolink.shared.domain.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechServiceQueryHandler {

    private final TechServiceRepository techServiceRepository;

    public TechServiceQueryHandler(
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

    public TechServiceDto handle(GetTechServiceQuery query) {

        return techServiceRepository.findById(query.serviceId())
                .map(TechServiceAssembler::toDto)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Service " + query.serviceId() + " not found"
                        )
                );
    }
}