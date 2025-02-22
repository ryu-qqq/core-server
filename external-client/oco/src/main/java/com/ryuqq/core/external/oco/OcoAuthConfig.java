package com.ryuqq.core.external.oco;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ryuqq.core.external.FeignRequestLoggingInterceptor;

import feign.RequestInterceptor;

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
	public FeignRequestLoggingInterceptor ocoAuthFeignRequestLoggingInterceptor() {
		return new FeignRequestLoggingInterceptor();
	}

}
