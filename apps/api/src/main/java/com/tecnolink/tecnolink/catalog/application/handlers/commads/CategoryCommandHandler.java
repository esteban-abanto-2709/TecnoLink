package com.tecnolink.tecnolink.catalog.application.handlers.commands;

import com.tecnolink.tecnolink.catalog.application.assemblers.CategoryAssembler;
import com.tecnolink.tecnolink.catalog.application.commands.CreateCategoryCommand;
import com.tecnolink.tecnolink.catalog.application.commands.RenameCategoryCommand;
import com.tecnolink.tecnolink.catalog.application.commands.SetCategoryActiveCommand;
import com.tecnolink.tecnolink.catalog.application.dtos.CategoryDto;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Category;
import com.tecnolink.tecnolink.catalog.domain.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryCommandHandler {

    private final CategoryRepository categoryRepository;

    public CategoryCommandHandler(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDto handle(CreateCategoryCommand command) {
        boolean exists = categoryRepository.findAll()
                .stream()
                .anyMatch(category ->
                        category.getName().equalsIgnoreCase(command.name().trim()));

        if (exists) {
            throw new IllegalArgumentException(
                    "A category with the same name already exists"
            );
        }

        String id = UUID.randomUUID().toString();

        Category category = new Category(
                id,
                command.name(),
                command.kind(),
                null
        );

        Category savedCategory = categoryRepository.save(category);

        return CategoryAssembler.toDto(savedCategory);
    }

    public CategoryDto handle(RenameCategoryCommand command) {
        Category category = categoryRepository.findById(command.categoryId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Category not found")
                );

        boolean exists = categoryRepository.findAll()
                .stream()
                .anyMatch(existingCategory ->
                        !existingCategory.getId().equals(category.getId())
                                && existingCategory.getName()
                                .equalsIgnoreCase(command.name().trim()));

        if (exists) {
            throw new IllegalArgumentException(
                    "A category with the same name already exists"
            );
        }

        category.rename(command.name());

        Category savedCategory = categoryRepository.save(category);

        return CategoryAssembler.toDto(savedCategory);
    }

    public CategoryDto handle(SetCategoryActiveCommand command) {
        Category category = categoryRepository.findById(command.categoryId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Category not found")
                );

        if (command.active()) {
            category.activate();
        } else {
            category.deactivate();
        }

        Category savedCategory = categoryRepository.save(category);

        return CategoryAssembler.toDto(savedCategory);
    }
}