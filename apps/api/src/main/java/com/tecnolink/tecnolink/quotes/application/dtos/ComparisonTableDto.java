package com.tecnolink.tecnolink.quotes.application.dtos;

import java.util.List;

public record ComparisonTableDto(Long comparisonId,
                                 List<String> productIds,
                                 List<ComparisonRowDto> rows) {}
