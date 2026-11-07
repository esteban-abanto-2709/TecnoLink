package com.tecnolink.tecnolink.quotes.application.dtos;

import java.time.LocalDate;
import java.util.List;

public record ComparisonDto(Long id,
                            String clientId,
                            LocalDate createdOn,
                            List<String> productIds) {}
