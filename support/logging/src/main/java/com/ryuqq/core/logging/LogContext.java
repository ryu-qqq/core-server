package com.ryuqq.core.logging;

import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public class LogContext {

	private static final ThreadLocal<Deque<LogEntry>> logEntryStack = ThreadLocal.withInitial(ConcurrentLinkedDeque::new);

	private LogContext() {
	}

	/** 로그 컨텍스트 초기화 */
	static void initialize() {
		logEntryStack.get().clear();
	}

	/** 로그 추가 */
	static void addEntry(LogEntry entry) {
		logEntryStack.get().push(entry);
	}

	/** 현재 로그 엔트리 목록 가져오기 */
	static List<LogEntry> getLogEntries() {
		return List.copyOf(logEntryStack.get());
	}

	/** 로그 컨텍스트 제거 */
	static void clear() {
		logEntryStack.remove();
	}

}
