package com.ryuqq.core.alert;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ErrorSlackNotificationService extends AbstractSlackNotificationService<String> {

	public ErrorSlackNotificationService(@Value("${slack.token}") String token) {
		super(token);
	}

	@Override
	protected String formatMessage(String message) {
		String errorHeader = """
        *🔴 Consolidated Error Report 🔴*
        Below are the detailed error logs:
        -------------------------------
        """;

		String errorFooter = """
        -------------------------------
        *End of Error Report*
        """;

		return errorHeader + message + errorFooter;
	}

	@Override
	public void notify(String payload) {
		notifySlack(payload);
	}
}
