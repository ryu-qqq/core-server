package com.ryuqq.core.logging;

import java.util.Map;

import org.springframework.boot.logging.LogLevel;

public class LogEntryFactory {


	public static SqlLogEntry createSqlLogEntry(
		String traceId,
		String layer,
		String sql,
		Map<String, Object> args,
		long executionTime,
		String errorMessage,
		LogLevel logLevel,
		String dataSourceName,
		String connectionId,
		int isolationLevel,
		String queryPhase
	) {
		return new SqlLogEntry(traceId, layer, sql, args, executionTime, errorMessage, logLevel, dataSourceName, connectionId, isolationLevel, queryPhase);
	}

	public static AopLogEntry createAopLogEntry(
		String traceId,
		String layer,
		String className,
		String methodName,
		Map<String, Object> args,
		Throwable exception,
		long executionTime
	) {
		return new AopLogEntry(traceId, layer, className, methodName, args, exception, executionTime);
	}

}
