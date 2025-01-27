package com.ryuqq.core.external.sellic;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.ryuqq.core.external.CustomFeignLogger;
import com.ryuqq.core.external.FeignRequestLoggingInterceptor;

import feign.Logger;

@ConfigurationProperties(prefix = "sellic")
public class SellicConfig {


	@Bean
    public SellicRequestInterceptor sellicRequestInterceptor() {
        return new SellicRequestInterceptor();
    }

	@Bean
	public Logger sellicFeignLogger() {
		return new CustomFeignLogger();
	}

	@Bean
	public Logger.Level sellicFeignLoggerLevel() {
		return Logger.Level.FULL;
	}

	@Bean
	public FeignRequestLoggingInterceptor sellicFeignRequestLoggingInterceptor() {
		return new FeignRequestLoggingInterceptor();
	}

}
