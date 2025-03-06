package com.ryuqq.core.external;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

@EnableFeignClients(basePackages = "com.ryuqq.core.external")
@Configuration
public class FeignClientsConfiguration {

	@Bean
	public Logger.Level loggerLevel() {
		return Logger.Level.FULL;
	}

}
