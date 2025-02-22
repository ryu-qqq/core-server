package com.ryuqq.core.external.sellic;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.ryuqq.core.external.FeignRequestLoggingInterceptor;



@ConfigurationProperties(prefix = "sellic")
public class SellicConfig {


	@Bean
    public SellicRequestInterceptor sellicRequestInterceptor() {
        return new SellicRequestInterceptor();
    }

	@Bean
	public FeignRequestLoggingInterceptor sellicFeignRequestLoggingInterceptor() {
		return new FeignRequestLoggingInterceptor();
	}

}
