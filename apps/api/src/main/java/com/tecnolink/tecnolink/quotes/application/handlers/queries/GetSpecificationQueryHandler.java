package com.tecnolink.tecnolink.quotes.application.handlers.queries;

import com.tecnolink.tecnolink.quotes.application.assemblers.QuotesDtoAssembler;
import com.tecnolink.tecnolink.quotes.application.dtos.SpecificationDto;
import com.tecnolink.tecnolink.quotes.application.queries.GetSpecificationQuery;
import com.tecnolink.tecnolink.quotes.domain.exceptions.SpecificationNotFoundException;
import com.tecnolink.tecnolink.quotes.domain.repositories.SpecificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GetSpecificationQueryHandler {
    private final SpecificationRepository specifications;

    public GetSpecificationQueryHandler(SpecificationRepository specifications) {
        this.specifications = specifications;
    }

    public SpecificationDto handle(GetSpecificationQuery q) {
        return QuotesDtoAssembler.toDto(specifications.findById(q.id()).orElseThrow(() -> new SpecificationNotFoundException(q.id())));
    }
}
