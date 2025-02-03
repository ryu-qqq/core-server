package com.ryuqq.core.storage.db;

import com.ryuqq.core.enums.LogLevel;

public class QueryPerformanceEvaluator {

	private QueryPerformanceEvaluator() {
	}

	public static LogLevel evaluate(long executionTime, long slowQueryThresholdMs, long criticalQueryThresholdMs) {
		if (executionTime > criticalQueryThresholdMs) {
			return LogLevel.ERROR;
		} else if (executionTime > slowQueryThresholdMs) {
			return LogLevel.WARN;
		} else {
			return LogLevel.INFO;
		}
	}

}
