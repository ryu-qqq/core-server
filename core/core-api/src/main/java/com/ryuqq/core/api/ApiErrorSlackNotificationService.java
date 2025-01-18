package com.ryuqq.core.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ryuqq.core.alert.AbstractSlackNotificationService;
import com.ryuqq.core.alert.AlertMessage;
import com.ryuqq.core.logging.AopLogEntry;

@Service
public class ApiErrorSlackNotificationService extends AbstractSlackNotificationService<AopLogEntry> {

	private static final String API_LAYER = "API";

	public ApiErrorSlackNotificationService(@Value("${slack.token}") String token) {
		super(token);
	}

	@Override
	protected String formatMessage(AopLogEntry logEntry) {
		String errorMessage = logEntry.exception().getMessage();

		AlertMessage alertMessage = new AlertMessage(
			logEntry.traceId(),
			logEntry.layer(),
			API_LAYER,
			logEntry.className(),
			logEntry.methodName(),
			errorMessage,
			logEntry.args()
		);

		return alertMessage.formatForSlack();
	}

	public void sendErrorAlert(AopLogEntry logEntry) {
		notifySlack(logEntry);
	}

}
