package com.ryuqq.core.alert;

public class SlackNotificationException extends RuntimeException {

	public SlackNotificationException(String message) {
		super(message);
	}

	public SlackNotificationException(String message, Throwable cause) {
		super(message, cause);
	}
}
