package com.ryuqq.core.logging;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class LogContext {

	private static final ThreadLocal<Deque<AopLogEntry>> logEntryStack = ThreadLocal.withInitial(ArrayDeque::new);

	public static void initialize(AopLogEntry entry) {
		logEntryStack.get().push(entry);
	}

	public static void addEntry(AopLogEntry entry) {
		logEntryStack.get().push(entry);
	}

	public static Deque<AopLogEntry> getLogEntries() {
		return new ArrayDeque<>(logEntryStack.get());
	}

	public static void clear() {
		logEntryStack.remove();
	}


	public static String generateNestedLogString() {
		List<AopLogEntry> list = getLogEntries().stream()
			.filter(entry -> entry.getLogLevel().isLogRequired())
			.toList();

		return LogFormatter.formatNestedLog(list);


	}


}
