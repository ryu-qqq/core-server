package com.ryuqq.core.external.buyma;

import org.springframework.context.annotation.Bean;

import feign.Logger;

public class BuyMaConfig {

	@Bean
	public BuyMaRequestInterceptor buyMaRequestInterceptor() {
		return new BuyMaRequestInterceptor();
	}

	@Bean
	public Logger buyMaFeignLogger() {
		return new BuyMaFeignLogger();
	}
}
