package com.ryuqq.core.api.config;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

@Configuration
public class VirtualThreadAsyncConfig implements AsyncConfigurer {

	@Bean(name = "virtualThreadExecutor")
	public Executor virtualThreadExecutor() {
		return runnable -> {
			Map<String, String> contextMap = MDC.getCopyOfContextMap();

			Executors.newVirtualThreadPerTaskExecutor().execute(() -> {
				try {
					if (contextMap != null) {
						MDC.setContextMap(contextMap);
					}
					runnable.run();
				} finally {
					MDC.clear();
				}
			});
		};
	}

	// @Bean
	// public ExecutorService virtualThreadExecutorService() {
	// 	return Executors.newVirtualThreadPerTaskExecutor();
	// }
	//
	//
	//
	// @Bean(name = "virtualThreadExecutor")
	// public Executor virtualThreadExecutor(ExecutorService virtualThreadExecutorService) {
	// 	return runnable -> {
	// 		Map<String, String> contextMap = MDC.getCopyOfContextMap();
	//
	// 		virtualThreadExecutorService.execute(() -> {
	// 			try {
	// 				if (contextMap != null) {
	// 					MDC.setContextMap(contextMap);
	// 				}
	// 				runnable.run();
	// 			} finally {
	// 				MDC.clear();
	// 			}
	// 		});
	// 	};
	// }

}
