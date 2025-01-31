package com.ryuqq.core.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryLogger {

	private static final Logger log = LoggerFactory.getLogger("SLOW_QUERY");

	private QueryLogger() {}

	public static void log(LogEntry logEntry) {
		String formattedLog = LogFormatter.format(logEntry);
		if (formattedLog != null && !formattedLog.isEmpty()) {
			switch (logEntry.getLogLevel()) {
				case ERROR -> {
					log.error("\n{}", formattedLog);
				}
				case WARN -> log.warn(formattedLog);
				case INFO -> log.info(formattedLog);
			}
		}
	}

}
