package com.ryuqq.core.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsyncConfig {

	@Bean(name = "asyncThreadPoolTaskExecutor")
	public ThreadPoolTaskExecutor asyncThreadPoolTaskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(2);
		taskExecutor.setMaxPoolSize(4);
		taskExecutor.setQueueCapacity(10);
		taskExecutor.setThreadNamePrefix("AsyncThread-");
		taskExecutor.setTaskDecorator(new MdcTaskDecorator());
		taskExecutor.initialize();
		return taskExecutor;
	}


}
