package com.ryuqq.core.logging;

import java.util.List;

public class LogContextManager {

	private LogContextManager() {}

	public static void initialize() {
		LogContext.initialize();
	}

	/** 어떤 로그든 추가 (AOP, HTTP 등) */
	public static void logToContext(LogEntry logEntry) {
		LogContext.addEntry(logEntry);
	}

	/** 로그 컨텍스트 제거 */
	public static void clear() {
		LogContext.clear();
	}

	public static List<LogEntry> getLogEntries(){
		return LogContext.getLogEntries();
	}

}
