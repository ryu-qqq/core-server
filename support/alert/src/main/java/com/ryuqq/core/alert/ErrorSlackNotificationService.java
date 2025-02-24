package com.ryuqq.core.alert;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.monikit.core.ErrorLogNotifier;
import com.monikit.core.ExceptionLog;

@Component
public class ErrorSlackNotificationService extends AbstractSlackNotificationService<String> implements
	ErrorLogNotifier {

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

	@Override
	public void onErrorLogDetected(ExceptionLog logEntry) {
		String exceptionLog = ExceptionLogFormatter.formatExceptionLog(logEntry);
		notifySlack(exceptionLog);
	}

}
