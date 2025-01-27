package com.ryuqq.core.domain.product.core;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.DomainEventPublisher;

@Component
public class ProductGroupEventPublisher {

	private final DomainEventPublisher eventPublisher;

	public ProductGroupEventPublisher(DomainEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	public void publishEvent(Object event) {
		eventPublisher.publish(event);
	}

}
