package com.ryuqq.core.external;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import feign.Logger;

@EnableFeignClients(basePackages = "com.ryuqq.core.external")
@Configuration
public class FeignClientsConfiguration {

	@Bean
	@Primary
	public Logger feignLogger() {
		return new CustomFeignLogger();
	}

}
