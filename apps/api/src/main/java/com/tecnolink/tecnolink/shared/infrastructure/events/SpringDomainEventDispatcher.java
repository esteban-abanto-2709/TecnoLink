package com.tecnolink.tecnolink.shared.infrastructure.events;

import com.tecnolink.tecnolink.shared.domain.events.DomainEvent;
import com.tecnolink.tecnolink.shared.domain.events.DomainEventDispatcher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Component
public class SpringDomainEventDispatcher implements DomainEventDispatcher {

    private final ApplicationEventPublisher eventPublisher;

    public SpringDomainEventDispatcher(
            ApplicationEventPublisher eventPublisher
    ) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void dispatch(DomainEvent event) {

        if (TransactionSynchronizationManager.isSynchronizationActive()) {

            TransactionSynchronizationManager.registerSynchronization(
                    new TransactionSynchronization() {

                        @Override
                        public void afterCommit() {
                            eventPublisher.publishEvent(event);
                        }
                    }
            );

        } else {
            eventPublisher.publishEvent(event);
        }
    }
}