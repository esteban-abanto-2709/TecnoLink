package com.tecnolink.tecnolink.catalog.application.handlers.queries;

import com.tecnolink.tecnolink.catalog.application.assemblers.CategoryAssembler;
import com.tecnolink.tecnolink.catalog.application.dtos.CategoryDto;
import com.tecnolink.tecnolink.catalog.application.queries.GetCategoriesQuery;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Category;
import com.tecnolink.tecnolink.catalog.domain.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCategoriesQueryHandler {

    private final CategoryRepository categoryRepository;

    public GetCategoriesQueryHandler(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDto> handle(GetCategoriesQuery query) {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryAssembler::toDto)
                .toList();
    }
}
