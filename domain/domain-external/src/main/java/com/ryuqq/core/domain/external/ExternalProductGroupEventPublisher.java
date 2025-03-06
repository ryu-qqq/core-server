package com.ryuqq.core.domain.external;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.DomainEventPublisher;

@Component
public class ExternalProductGroupEventPublisher {

	private final DomainEventPublisher eventPublisher;

	public ExternalProductGroupEventPublisher(DomainEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	public void publishEvent(Object event) {
		eventPublisher.publish(event);
	}

}
