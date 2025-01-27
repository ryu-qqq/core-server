package com.ryuqq.core.domain;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class DomainEventPublisher {

	private final ApplicationEventPublisher applicationEventPublisher;

	public DomainEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	/**
	 * 도메인 이벤트 발행
	 *
	 * @param event 발행할 이벤트
	 */
	public void publish(Object event) {
		applicationEventPublisher.publishEvent(event);
	}


}
