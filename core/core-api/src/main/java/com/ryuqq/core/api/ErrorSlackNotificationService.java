package com.ryuqq.core.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ryuqq.core.alert.AbstractSlackNotificationService;
import com.ryuqq.core.alert.AlertMessage;
import com.ryuqq.core.logging.AopLogEntry;

@Service
public class ErrorSlackNotificationService extends AbstractSlackNotificationService<List<AopLogEntry>> {

	public ErrorSlackNotificationService(@Value("${slack.token}") String token) {
		super(token);
	}

	@Override
	protected String formatMessage(List<AopLogEntry> logEntries) {
		String errorHeader = """
        *🔴 Consolidated Error Report 🔴*
        Below are the detailed error logs:
        -------------------------------
        """;

		String errorFooter = """
        -------------------------------
        *End of Error Report*
        """;

		String errorDetails = logEntries.stream()
			.map(l -> new AlertMessage(
				"Error occurred",
				l.traceId(),
				l.layer(),
				l.className(),
				l.methodName(),
				l.exception().getMessage(),
				l.args()).formatForSlack())
			.collect(Collectors.joining("\n"));

		return errorHeader + errorDetails + errorFooter;
	}

	public void sendErrorAlert(List<AopLogEntry> logEntries) {
		notifySlack(logEntries);
	}

}
