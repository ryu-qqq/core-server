package com.ryuqq.core.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ryuqq.core.alert.AbstractSlackNotificationService;
import com.ryuqq.core.alert.AlertMessage;
import com.ryuqq.core.logging.AopLogEntry;

@Service
public class DomainErrorSlackNotificationService extends AbstractSlackNotificationService<AopLogEntry> {

	private static final String DOMAIN_LAYER = "DOMAIN";

	public DomainErrorSlackNotificationService(@Value("${slack.token}") String token) {
		super(token);
	}

	@Override
	protected String formatMessage(AopLogEntry logEntry) {
		String errorMessage = logEntry.exception().getMessage();

		AlertMessage alertMessage = new AlertMessage(
			logEntry.traceId(),
			logEntry.layer(),
			DOMAIN_LAYER,
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
