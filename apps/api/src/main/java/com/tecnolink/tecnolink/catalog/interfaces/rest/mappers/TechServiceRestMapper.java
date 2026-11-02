package com.tecnolink.tecnolink.catalog.interfaces.rest.mappers;

import com.tecnolink.tecnolink.catalog.application.commands.CreateTechServiceCommand;
import com.tecnolink.tecnolink.catalog.application.commands.UpdateTechServiceCommand;
import com.tecnolink.tecnolink.catalog.interfaces.rest.dto.CreateTechServiceRequest;
import com.tecnolink.tecnolink.catalog.interfaces.rest.dto.UpdateTechServiceRequest;

public final class TechServiceRestMapper {

    private TechServiceRestMapper() {
    }

    public static CreateTechServiceCommand toCommand(
            CreateTechServiceRequest request
    ) {
        return new CreateTechServiceCommand(
                request.name(),
                request.categoryId(),
                request.supplierId(),
                request.price(),
                request.description(),
                request.images(),
                request.pricing(),
                request.coverage(),
                request.modality(),
                request.estimatedDuration()
        );
    }

    public static UpdateTechServiceCommand toCommand(
            String serviceId,
            UpdateTechServiceRequest request
    ) {
        return new UpdateTechServiceCommand(
                serviceId,
                request.name(),
                request.price(),
                request.pricing(),
                request.description(),
                request.coverage()
        );
    }
}