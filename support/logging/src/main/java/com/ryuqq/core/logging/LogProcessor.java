package com.ryuqq.core.logging;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.events.SlackErrorAlertMessageEvent;

@Component
public class LogProcessor {

	private final LoggingEventPublisher loggingEventPublisher;

	public LogProcessor(LoggingEventPublisher loggingEventPublisher) {
		this.loggingEventPublisher = loggingEventPublisher;
	}

	public void processLogs(List<LogEntry> logEntries) {
		for (LogEntry entry : logEntries) {
			if (entry instanceof SqlLogEntry sqlLog) {
				handleSqlLog(sqlLog);
			} else {
				handleGeneralLog(entry);
			}
		}

		List<AopLogEntry> errorLogs = logEntries.stream()
			.filter(entry -> entry instanceof AopLogEntry)
			.map(entry -> (AopLogEntry) entry)
			.filter(aopLog -> aopLog.getLogLevel().isLogRequired())
			.toList();

		if (!errorLogs.isEmpty()) {
			String slackLog = SlackLogFormatter.formatForSlack(errorLogs);
			loggingEventPublisher.publish(new SlackErrorAlertMessageEvent(slackLog));
		}
	}

	private void handleSqlLog(SqlLogEntry sqlLog) {
		String format = SqlLogFormatter.format(sqlLog);
		switch (sqlLog.getLogLevel()) {
			case ERROR -> Loggers.SLOW_QUERY_LOGGER.error(format);
			case WARN -> Loggers.SLOW_QUERY_LOGGER.warn(format);
		}
	}

	private void handleGeneralLog(LogEntry entry) {
		if (entry.getLogLevel().isLogRequired()) {
			Loggers.ERROR_LOGGER.error(KibanaLogFormatter.formatForKibana(List.of(entry)));
		} else {
			Loggers.JSON_LOGGER.info(KibanaLogFormatter.formatForKibana(List.of(entry)));
		}
	}

}
