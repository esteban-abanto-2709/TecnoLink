package com.tecnolink.tecnolink.quotes.application.dtos;

public record SpecificationDto(Long id,
                               String productId,
                               String name,
                               String value,
                               String unit,
                               String text) {}
