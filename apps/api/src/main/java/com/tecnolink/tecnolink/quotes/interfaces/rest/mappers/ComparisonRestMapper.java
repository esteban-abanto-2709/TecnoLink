package com.tecnolink.tecnolink.quotes.interfaces.rest.mappers;

import com.tecnolink.tecnolink.quotes.application.commands.AddComparisonProductCommand;
import com.tecnolink.tecnolink.quotes.application.commands.CreateComparisonCommand;
import com.tecnolink.tecnolink.quotes.interfaces.rest.resources.ComparisonCreateRequest;
import com.tecnolink.tecnolink.quotes.interfaces.rest.resources.ComparisonProductRequest;

public final class ComparisonRestMapper {
    private ComparisonRestMapper() {
    }

    public static CreateComparisonCommand toCommand(ComparisonCreateRequest r) {
        return new CreateComparisonCommand(r.clientId(), r.productIds());
    }

    public static AddComparisonProductCommand toCommand(Long id, ComparisonProductRequest r) {
        return new AddComparisonProductCommand(id, r.productId());
    }
}
