package com.ryuqq.core.external.oco;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.ryuqq.core.external.FeignRequestLoggingInterceptor;

import feign.Logger;

@ConfigurationProperties(prefix = "oco")
public class OcoConfig {

	private final OcoAuthManager ocoAuthManager;

	public OcoConfig(OcoAuthManager ocoAuthManager) {
		this.ocoAuthManager = ocoAuthManager;
	}

	@Bean
	public OcoRequestInterceptor ocoRequestInterceptor() {
		return new OcoRequestInterceptor(ocoAuthManager);
	}

	@Bean
	public Logger ocoFeignLogger() {
		return new OcoFeignLogger();
	}

	@Bean
	public FeignRequestLoggingInterceptor ocoFeignRequestLoggingInterceptor() {
		return new FeignRequestLoggingInterceptor();
	}

}
