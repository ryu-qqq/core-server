package com.ryuqq.core.logging;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.ryuqq.core.utils.TraceIdHolder;

public class LogFormatter {


	public static String format(LogEntry logEntry) {
		if (logEntry instanceof SqlLogEntry) {
			return formatSqlLogEntry((SqlLogEntry) logEntry);
		} else if (logEntry instanceof AopLogEntry) {
			return formatAopLogEntry((AopLogEntry) logEntry);
		}

		return "";
	}

	private static String formatSqlLogEntry(SqlLogEntry logEntry) {
		return SqlLogFormatter.format(logEntry);
	}

	private static String formatAopLogEntry(AopLogEntry logEntry) {
		// 필요하다면 AOP 로그 단일 엔트리 형식화 로직 추가
		return String.format("Class: %s, Method: %s, Args: %s, ExecutionTime: %d ms",
			logEntry.getClassName(),
			logEntry.getMethodName(),
			logEntry.getArgs(),
			logEntry.getExecutionTime());
	}

	public static String formatNestedLog(List<AopLogEntry> logEntries) {
		List<AopLogEntryDto> entryDtos = logEntries.stream()
			.map(entry -> new AopLogEntryDto(
				entry.getClassName(),
				entry.getMethodName(),
				entry.getArgs(),
				entry.getExecutionTime(),
				entry.getException() != null ? entry.getException().getMessage() : null
			))
			.toList();

		if (entryDtos.isEmpty()) {
			return "No log entries available.";
		}

		AopLogEntryDto firstEntry = entryDtos.getLast();
		AopLogEntryDto lastEntry = entryDtos.getFirst();
		AopLogEntryDto longestEntry = entryDtos.stream()
			.max(Comparator.comparing(AopLogEntryDto::getExecutionTime))
			.orElse(firstEntry);

		String exceptionMessage = formatExceptionMessage(lastEntry.getExceptionMessage());

		return String.format(
			"""
				:pushpin: **TRACE ID**: %s
				:rocket: **START**: %s.%s (ExecutionTime: %d ms)
				:stopwatch: **LONGEST**: %s.%s (ExecutionTime: %d ms)
				:checkered_flag: **END**: %s.%s (ExecutionTime: %d ms)
				:warning: **EXCEPTION**: %s
			""",
			TraceIdHolder.getTraceId(),
			firstEntry.getClassName(), firstEntry.getMethodName(), firstEntry.getExecutionTime(),
			longestEntry.getClassName(), longestEntry.getMethodName(), longestEntry.getExecutionTime(),
			lastEntry.getClassName(), lastEntry.getMethodName(), lastEntry.getExecutionTime(),
			exceptionMessage
		);
	}

	private static String formatExceptionMessage(String exceptionMessage) {
		if (exceptionMessage == null || exceptionMessage.isEmpty()) {
			return "No exception message available.";
		}

		return List.of(exceptionMessage.split("\\| Cause:"))
			.stream()
			.distinct()
			.map(String::trim)
			.collect(Collectors.joining(" | Cause: "));
	}

}
