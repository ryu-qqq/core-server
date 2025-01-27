package com.ryuqq.core.external.oco;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ryuqq.core.external.CustomFeignLogger;
import com.ryuqq.core.external.FeignRequestLoggingInterceptor;

import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;

@Configuration
@ConfigurationProperties(prefix = "oco")
public class OcoAuthConfig {

	@Value("${oco.api-key}")
	private String apiKey;

	@Bean
	public RequestInterceptor authRequestInterceptor() {
		return template -> template.header("ApiKey", apiKey);
	}

	@Bean
	public ErrorDecoder ocoAuthErrorDecoder() {
		return new OcoAuthErrorDecoder();
	}

	@Bean
	public Logger ocoAuthFeignLogger() {
		return new CustomFeignLogger();
	}

	@Bean
	public Logger.Level ocoAuthFeignLoggerLevel() {
		return Logger.Level.FULL;
	}

	@Bean
	public FeignRequestLoggingInterceptor ocoAuthFeignRequestLoggingInterceptor() {
		return new FeignRequestLoggingInterceptor();
	}

}
