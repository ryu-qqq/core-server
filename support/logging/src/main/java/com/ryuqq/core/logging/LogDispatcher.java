package com.ryuqq.core.logging;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class LogDispatcher {

	private final LogProcessor logProcessor;

	public LogDispatcher(LogProcessor logProcessor) {
		this.logProcessor = logProcessor;
	}

	public void dispatchLogs() {
		List<LogEntry> entries = LogContextManager.getLogEntries()
			.stream().toList();

		if (!entries.isEmpty()) {
			logProcessor.processLogs(entries);
		}

		LogContextManager.clear();
	}

}
