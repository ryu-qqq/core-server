package com.ryuqq.core.logging;

import java.util.Comparator;
import java.util.List;

import com.ryuqq.core.utils.TraceIdHolder;

public class SlackLogFormatter {

	private static final int STACK_TRACE_LIMIT = 10;

	public static String formatForSlack(List<AopLogEntry> logEntries) {
		if (logEntries.isEmpty()) {
			return ":warning: No error log entries available.";
		}

		List<SlackLogFormatDto> entryDtos = logEntries.stream()
			.map(entry -> new SlackLogFormatDto(
				entry.getClassName(),
				entry.getMethodName(),
				entry.getArgs(),
				entry.getExecutionTime(),
				entry.getErrorMessage(),
				formatStackTrace(entry.getStackTrace()),
				entry.getCallerData(),
				entry.getHostName(),
				entry.getProcessId(),
				entry.getApplicationName()
			))
			.toList();

		SlackLogFormatDto firstEntry = entryDtos.getLast();
		SlackLogFormatDto lastEntry = entryDtos.getFirst();
		SlackLogFormatDto longestEntry = entryDtos.stream()
			.max(Comparator.comparing(SlackLogFormatDto::getExecutionTime))
			.orElse(firstEntry);

		return String.format(
			"""
			:pushpin: *TRACE ID*: `%s`
			:rocket: *START*: `%s.%s` (ExecutionTime: `%d ms`)
			:stopwatch: *LONGEST*: `%s.%s` (ExecutionTime: `%d ms`)
			:checkered_flag: *END*: `%s.%s` (ExecutionTime: `%d ms`)
			:warning: *EXCEPTION*: `%s`
			:page_with_curl: *Filtered Stack Trace*:
			```java
			%s
			```
			:computer: *Host*: `%s`
			:gear: *Process ID*: `%s`
			:factory: *Application*: `%s`
			""",
			TraceIdHolder.getTraceId(),
			firstEntry.getClassName(), firstEntry.getMethodName(), firstEntry.getExecutionTime(),
			longestEntry.getClassName(), longestEntry.getMethodName(), longestEntry.getExecutionTime(),
			lastEntry.getClassName(), lastEntry.getMethodName(), lastEntry.getExecutionTime(),
			lastEntry.getExceptionMessage(),
			lastEntry.getFilteredStackTrace(),
			lastEntry.getHostName(),
			lastEntry.getProcessId(),
			lastEntry.getApplicationName()
		);
	}


	private static String formatExceptionMessage(Throwable exception) {
		if (exception == null) {
			return "No exception message available.";
		}
		return exception.getClass().getName() + ": " + exception.getMessage();
	}

	private static String formatStackTrace(String stackTrace) {
		if (stackTrace == null || stackTrace.isEmpty()) {
			return "No stack trace available.";
		}
		String[] lines = stackTrace.split("\n");
		return String.join("\n", List.of(lines).subList(0, Math.min(lines.length, STACK_TRACE_LIMIT)));
	}


}
