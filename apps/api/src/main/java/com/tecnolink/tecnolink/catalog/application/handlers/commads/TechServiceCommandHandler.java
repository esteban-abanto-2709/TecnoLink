package com.tecnolink.tecnolink.catalog.application.handlers.commands;

import com.tecnolink.tecnolink.catalog.application.assemblers.TechServiceAssembler;
import com.tecnolink.tecnolink.catalog.application.commands.CreateTechServiceCommand;
import com.tecnolink.tecnolink.catalog.application.commands.UpdateTechServiceCommand;
import com.tecnolink.tecnolink.catalog.application.dtos.TechServiceDto;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.TechService;
import com.tecnolink.tecnolink.catalog.domain.repositories.TechServiceRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TechServiceCommandHandler {

    private final TechServiceRepository techServiceRepository;

    public TechServiceCommandHandler(TechServiceRepository techServiceRepository) {
        this.techServiceRepository = techServiceRepository;
    }

    public TechServiceDto handle(CreateTechServiceCommand command) {

        String id = UUID.randomUUID().toString();

        TechService service = new TechService(
                id,
                command.name(),
                command.categoryId(),
                command.supplierId(),
                command.price(),
                command.description(),
                command.images(),
                null,
                null,
                command.pricing(),
                command.coverage(),
                command.modality(),
                command.estimatedDuration()
        );

        TechService savedService = techServiceRepository.save(service);

        return TechServiceAssembler.toDto(savedService);
    }

    public TechServiceDto handle(UpdateTechServiceCommand command) {

        TechService service = techServiceRepository.findById(command.serviceId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Tech service not found")
                );

        service.update(
                command.name(),
                command.price(),
                command.pricing(),
                command.description(),
                command.coverage()
        );

        TechService savedService = techServiceRepository.save(service);

        return TechServiceAssembler.toDto(savedService);
    }
}