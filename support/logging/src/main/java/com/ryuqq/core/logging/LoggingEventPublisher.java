package com.ryuqq.core.logging;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class LoggingEventPublisher {

	private final ApplicationEventPublisher applicationEventPublisher;

	public LoggingEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
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
