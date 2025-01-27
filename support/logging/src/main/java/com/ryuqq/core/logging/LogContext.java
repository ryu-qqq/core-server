package com.ryuqq.core.logging;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;

public class LogContext {

	private static final ThreadLocal<Deque<AopLogEntry>> logEntryStack = ThreadLocal.withInitial(ArrayDeque::new);

	public static void initialize(String traceId, String layer, String className, String methodName, Map<String, Object> args) {
		AopLogEntry newEntry = new AopLogEntry(traceId, layer, className, methodName, args, null, 0);
		logEntryStack.get().push(newEntry);
	}

	public static boolean isTopLevelCall() {
		return logEntryStack.get().size() == 1;
	}

	public static void addNestedLogEntry(AopLogEntry nestedEntry) {
		logEntryStack.get().push(nestedEntry);
	}

	public static AopLogEntry getCurrentLogEntry() {
		return logEntryStack.get().peek();
	}

	public static Throwable getCurrentThrowable() {
		return logEntryStack.get().peek().exception();
	}

	public static List<AopLogEntry> getNestedLogEntries() {
		return new ArrayList<>(logEntryStack.get());
	}

	public static void finalizeEntry() {
		if (!logEntryStack.get().isEmpty()) {
			logEntryStack.get().pop();
		}
		if (logEntryStack.get().isEmpty()) {
			logEntryStack.remove();
		}
	}

	public static String generateNestedLogString() {
		StringBuilder sb = new StringBuilder();
		for (AopLogEntry entry : getNestedLogEntries()) {
			sb.append(entry.toString()).append("\n");
		}
		return sb.toString();
	}

}
