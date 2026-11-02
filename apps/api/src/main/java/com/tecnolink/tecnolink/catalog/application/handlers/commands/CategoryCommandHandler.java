package com.tecnolink.tecnolink.catalog.application.handlers.commands;

import com.tecnolink.tecnolink.catalog.application.assemblers.CategoryAssembler;
import com.tecnolink.tecnolink.catalog.application.commands.CreateCategoryCommand;
import com.tecnolink.tecnolink.catalog.application.commands.RenameCategoryCommand;
import com.tecnolink.tecnolink.catalog.application.commands.SetCategoryActiveCommand;
import com.tecnolink.tecnolink.catalog.application.dtos.CategoryDto;
import com.tecnolink.tecnolink.catalog.domain.events.CategoryCreatedEvent;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Category;
import com.tecnolink.tecnolink.catalog.domain.repositories.CategoryRepository;
import com.tecnolink.tecnolink.shared.domain.events.DomainEventDispatcher;
import com.tecnolink.tecnolink.shared.domain.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryCommandHandler {

    private final CategoryRepository categoryRepository;
    private final DomainEventDispatcher domainEventDispatcher;

    public CategoryCommandHandler(
            CategoryRepository categoryRepository,
            DomainEventDispatcher domainEventDispatcher
    ) {
        this.categoryRepository = categoryRepository;
        this.domainEventDispatcher = domainEventDispatcher;
    }

    public CategoryDto handle(CreateCategoryCommand command) {

        checkCategoryNameIsFree(command.name(), null);

        String id = UUID.randomUUID().toString();

        Category category = new Category(
                id,
                command.name(),
                command.kind(),
                null
        );

        Category savedCategory =
                categoryRepository.save(category);

        domainEventDispatcher.dispatch(
                new CategoryCreatedEvent(
                        savedCategory.getId(),
                        savedCategory.getName()
                )
        );

        return CategoryAssembler.toDto(savedCategory);
    }

    public CategoryDto handle(RenameCategoryCommand command) {

        Category category = categoryRepository
                .findById(command.categoryId())
                .orElseThrow(() ->
                        new NotFoundException(
                                "Category " + command.categoryId() + " not found"
                        )
                );

        checkCategoryNameIsFree(
                command.name(),
                command.categoryId()
        );

        category.rename(command.name());

        Category savedCategory =
                categoryRepository.save(category);

        return CategoryAssembler.toDto(savedCategory);
    }

    public CategoryDto handle(SetCategoryActiveCommand command) {

        Category category = categoryRepository
                .findById(command.categoryId())
                .orElseThrow(() ->
                        new NotFoundException(
                                "Category " + command.categoryId() + " not found"
                        )
                );

        if (command.active()) {
            category.activate();
        } else {
            category.deactivate();
        }

        Category savedCategory =
                categoryRepository.save(category);

        return CategoryAssembler.toDto(savedCategory);
    }

    private void checkCategoryNameIsFree(
            String name,
            String exceptId
    ) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Category name is required"
            );
        }

        String target = normalize(name);

        for (Category category : categoryRepository.findAll()) {

            if (!category.getId().equals(exceptId)
                    && normalize(category.getName()).equals(target)) {

                throw new IllegalArgumentException(
                        "Category " + name.trim() + " already exists"
                );
            }
        }
    }

    private String normalize(String text) {
        return text.trim().toLowerCase();
    }
}