package com.ryuqq.core.external.buyma;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;



@ConfigurationProperties(prefix = "buyma")
public class BuyMaConfig {

	@Bean
	public BuyMaRequestInterceptor buyMaRequestInterceptor() {
		return new BuyMaRequestInterceptor();
	}

}
