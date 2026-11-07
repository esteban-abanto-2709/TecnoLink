package com.tecnolink.tecnolink.quotes.interfaces.rest.mappers;

import com.tecnolink.tecnolink.quotes.application.commands.AddSpecificationCommand;
import com.tecnolink.tecnolink.quotes.interfaces.rest.resources.SpecificationCreateRequest;

public final class SpecificationRestMapper {
    private SpecificationRestMapper() {
    }

    public static AddSpecificationCommand toCommand(SpecificationCreateRequest r) {
        return new AddSpecificationCommand(r.productId(), r.name(), r.value(), r.unit());
    }
}
