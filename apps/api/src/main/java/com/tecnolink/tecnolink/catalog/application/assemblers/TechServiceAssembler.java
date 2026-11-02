package com.tecnolink.tecnolink.catalog.application.assemblers;

import com.tecnolink.tecnolink.catalog.application.dtos.TechServiceDto;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.TechService;

public final class TechServiceAssembler {

    private TechServiceAssembler() {
    }

    public static TechServiceDto toDto(TechService service) {
        return new TechServiceDto(
                service.getId(),
                service.getName(),
                service.getCategoryId(),
                service.getSupplierId(),
                service.getPrice(),
                service.getDescription(),
                service.getImages(),
                service.getPublishedOn(),
                service.getStatus(),
                service.getPricing(),
                service.getCoverage(),
                service.getModality(),
                service.getEstimatedDuration()
        );
    }
}