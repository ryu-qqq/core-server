package com.ryuqq.core.external;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

@EnableFeignClients(
	basePackages = "com.ryuqq.core.external",
	defaultConfiguration = GlobalFeignConfig.class
)
@Configuration
public class GlobalFeignConfig {

	@Bean
	public Logger feignLogger() {
		return new CustomFeignLogger();
	}

	@Bean
	public FeignRequestLoggingInterceptor feignRequestLoggingInterceptor() {
		return new FeignRequestLoggingInterceptor();
	}

}
