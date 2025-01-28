package com.ryuqq.core.api;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ApiEventPublisher {

	private final ApplicationEventPublisher applicationEventPublisher;

	public ApiEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	/**
	 * Api 이벤트 발행
	 *
	 * @param event 발행할 이벤트
	 */
	public void publish(Object event) {
		applicationEventPublisher.publishEvent(event);
	}


}
