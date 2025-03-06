package com.ryuqq.core.api.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.ryuqq.core.logging.MDCThreadContextHandler;

@Configuration
public class PlatformThreadConfig {

	private final MDCThreadContextHandler threadContextHandler;

	public PlatformThreadConfig(MDCThreadContextHandler threadContextHandler) {
		this.threadContextHandler = threadContextHandler;
	}

	@Bean(name = "asyncThreadPoolTaskExecutor")
	public Executor asyncThreadPoolTaskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(4);
		taskExecutor.setMaxPoolSize(8);
		taskExecutor.setQueueCapacity(20);
		taskExecutor.setThreadNamePrefix("PlatformThread-");
		taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		taskExecutor.initialize();

		return runnable -> taskExecutor.execute(threadContextHandler.propagateToChildThread(runnable));

	}

}
