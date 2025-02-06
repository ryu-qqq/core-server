package com.ryuqq.core.domain;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Component
public class MdcContextPropagatingExecutorService {

	private final ExecutorService delegate;

	public MdcContextPropagatingExecutorService() {
		this.delegate = Executors.newVirtualThreadPerTaskExecutor();
	}

	public void submit(Runnable task) {

		Map<String, String> contextMap = MDC.getCopyOfContextMap();
		delegate.submit(() -> {
			if (contextMap != null) {
				MDC.setContextMap(contextMap);
			}
			try {
				task.run();
			} finally {
				MDC.clear();
			}
		});
	}

}
