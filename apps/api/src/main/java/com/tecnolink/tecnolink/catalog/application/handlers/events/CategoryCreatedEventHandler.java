package com.tecnolink.tecnolink.catalog.application.handlers.events;

import com.tecnolink.tecnolink.catalog.domain.events.CategoryCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CategoryCreatedEventHandler {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(CategoryCreatedEventHandler.class);

    @EventListener
    public void handle(CategoryCreatedEvent event) {

        LOGGER.info(
                "Category created successfully. ID: {}, Name: {}",
                event.categoryId(),
                event.categoryName()
        );
    }
}