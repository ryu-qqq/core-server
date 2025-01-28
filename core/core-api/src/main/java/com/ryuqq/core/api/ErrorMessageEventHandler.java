package com.ryuqq.core.api;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.ryuqq.core.events.SlackErrorAlertMessageEvent;

@Component
public class ErrorMessageEventHandler {

	private final NotificationService<String> notificationService;

	public ErrorMessageEventHandler(NotificationService<String> notificationService) {
		this.notificationService = notificationService;
	}

	@EventListener
	@Async("virtualThreadExecutor")
	public void handleErrorMessageEvent(SlackErrorAlertMessageEvent event) {
		notificationService.notify(event.message());
	}

}
