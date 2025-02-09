package com.ryuqq.core.domain.product.core;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ProductGroupContextEventPublisher {

	private final ApplicationEventPublisher applicationEventPublisher;

	public ProductGroupContextEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	public void publish(Object event) {
		applicationEventPublisher.publishEvent(event);
	}

}
