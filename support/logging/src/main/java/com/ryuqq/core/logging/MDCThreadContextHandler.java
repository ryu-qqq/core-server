package com.ryuqq.core.logging;

import java.util.Map;
import java.util.concurrent.Callable;

import org.slf4j.MDC;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.monikit.core.DefaultThreadContextHandler;
import com.monikit.core.LogEntryContextManager;
import com.monikit.starter.LogContextScope;

@Component
@Primary
public class MDCThreadContextHandler extends DefaultThreadContextHandler {

	private final LogEntryContextManager logEntryContextManager;

	public MDCThreadContextHandler(LogEntryContextManager logEntryContextManager) {
		this.logEntryContextManager = logEntryContextManager;
	}

	public Runnable propagateToChildThread(Runnable task) {
		Map<String, String> contextMap = MDC.getCopyOfContextMap();

		return () -> {
			try (LogContextScope scope = new LogContextScope(logEntryContextManager)) {
				if (contextMap != null) {
					MDC.setContextMap(contextMap);
				}
				try {
					task.run();
				} finally {
					MDC.clear();
				}
			}
		};
	}

	public <T> Callable<T> propagateToChildThread(Callable<T> task) {
		Map<String, String> contextMap = MDC.getCopyOfContextMap();

		return () -> {
			try (LogContextScope scope = new LogContextScope(logEntryContextManager)) {
				if (contextMap != null) {
					MDC.setContextMap(contextMap);
				}
				try {
					return task.call();
				} finally {
					MDC.clear();
				}
			}
		};
	}

}
