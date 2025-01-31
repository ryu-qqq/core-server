package com.ryuqq.core.logging;

import java.util.Comparator;
import java.util.List;

import com.ryuqq.core.utils.TraceIdHolder;

public class SlackLogFormatter {

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
				entry.getException() != null ? entry.getException().getMessage() : null,
				entry.getStackTrace(),
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

		String exceptionMessage = formatExceptionMessage(lastEntry.getExceptionMessage());

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
			exceptionMessage,
			lastEntry.getFilteredStackTrace(),
			lastEntry.getHostName(),
			lastEntry.getProcessId(),
			lastEntry.getApplicationName()
		);
	}

	private static String formatExceptionMessage(String exceptionMessage) {
		if (exceptionMessage == null || exceptionMessage.isEmpty()) {
			return "No exception message available.";
		}
		return exceptionMessage.replaceAll("\n", " ");
	}

}
