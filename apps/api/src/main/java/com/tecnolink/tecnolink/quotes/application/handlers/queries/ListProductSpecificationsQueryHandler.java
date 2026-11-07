package com.tecnolink.tecnolink.quotes.application.handlers.queries;

import com.tecnolink.tecnolink.quotes.application.assemblers.QuotesDtoAssembler;
import com.tecnolink.tecnolink.quotes.application.dtos.SpecificationDto;
import com.tecnolink.tecnolink.quotes.application.queries.ListProductSpecificationsQuery;
import com.tecnolink.tecnolink.quotes.domain.repositories.SpecificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ListProductSpecificationsQueryHandler {
    private final SpecificationRepository specifications;

    public ListProductSpecificationsQueryHandler(SpecificationRepository specifications) {
        this.specifications = specifications;
    }

    public List<SpecificationDto> handle(ListProductSpecificationsQuery q) {
        return specifications.findByProductId(q.productId()).stream().map(QuotesDtoAssembler::toDto).toList();
    }
}
