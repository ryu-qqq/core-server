package com.ryuqq.core.storage.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ryuqq.core.alert.AbstractSlackNotificationService;
import com.ryuqq.core.alert.AlertMessage;
import com.ryuqq.core.logging.AopLogEntry;

@Service
public class RdsErrorSlackNotificationService extends AbstractSlackNotificationService<AopLogEntry> {

	private static final String STORAGE_LAYER = "STORAGE";

	public RdsErrorSlackNotificationService(@Value("${slack.token}") String token) {
		super(token);
	}

	@Override
	protected String formatMessage(AopLogEntry logEntry) {
		String errorMessage = logEntry.exception().getMessage();

		AlertMessage alertMessage = new AlertMessage(
			logEntry.traceId(),
			logEntry.layer(),
			STORAGE_LAYER,
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
