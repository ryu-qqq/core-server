package com.ryuqq.core.logging;

import org.aspectj.lang.ProceedingJoinPoint;

import com.ryuqq.core.enums.LogLevel;
import com.ryuqq.core.utils.AopUtils;
import com.ryuqq.core.utils.TraceIdHolder;

public class LogContextManager {

	private LogContextManager() {}

	public static void initialize(ProceedingJoinPoint joinPoint, String layer) {

		LogContext.initialize(AopLogEntryFactory.createAopLogEntry(
			TraceIdHolder.getTraceId(),
			layer,
			joinPoint.getTarget().getClass().getSimpleName(),
			joinPoint.getSignature().getName(),
			AopUtils.extractArgs(joinPoint),
			null,
			0,
			LogLevel.INFO,
			extractCallerData(joinPoint)
		));
	}

	public static String finalizeAndLog() {
		if (!LogContext.getLogEntries().isEmpty()) {
			String nestedLog = LogContext.generateNestedLogString();
			LogContext.clear();
			return nestedLog;
		}
		return null;
	}

	public static void logToContext(ProceedingJoinPoint joinPoint, Throwable exception, long startTime, String layer) {
		long executionTime = System.currentTimeMillis() - startTime;

		AopLogEntry logEntry = AopLogEntryFactory.createAopLogEntry(
			TraceIdHolder.getTraceId(),
			layer,
			joinPoint.getTarget().getClass().getSimpleName(),
			joinPoint.getSignature().getName(),
			AopUtils.extractArgs(joinPoint),
			exception,
			executionTime,
			LogLevel.ERROR,
			extractCallerData(joinPoint)
		);

		LogContext.addEntry(logEntry);
	}

	public static void clear() {
		LogContext.clear();
	}

	private static String extractCallerData(ProceedingJoinPoint joinPoint) {
		return joinPoint.getSignature().toShortString();
	}

}
