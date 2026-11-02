package com.tecnolink.tecnolink.catalog.application.handlers.commands;

import com.tecnolink.tecnolink.catalog.application.assemblers.TechServiceAssembler;
import com.tecnolink.tecnolink.catalog.application.commands.CreateTechServiceCommand;
import com.tecnolink.tecnolink.catalog.application.commands.UpdateTechServiceCommand;
import com.tecnolink.tecnolink.catalog.application.dtos.TechServiceDto;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.TechService;
import com.tecnolink.tecnolink.catalog.domain.model.enums.ListingStatus;
import com.tecnolink.tecnolink.catalog.domain.repositories.TechServiceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class TechServiceCommandHandler {

    private final TechServiceRepository techServiceRepository;

    public TechServiceCommandHandler(
            TechServiceRepository techServiceRepository
    ) {
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
                LocalDate.now(),
                ListingStatus.ACTIVE,
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
                        new IllegalArgumentException(
                                "Service " + command.serviceId() + " not found"
                        )
                );

        service.update(
                command.name(),
                command.price(),
                command.pricing(),
                command.description(),
                command.coverage()
        );

        TechService updatedService = techServiceRepository.save(service);

        return TechServiceAssembler.toDto(updatedService);
    }
}