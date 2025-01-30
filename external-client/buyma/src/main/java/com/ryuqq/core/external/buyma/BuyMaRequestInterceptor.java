package com.ryuqq.core.external.buyma;

import org.springframework.beans.factory.annotation.Value;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class BuyMaRequestInterceptor implements RequestInterceptor {

	@Value("${buyMa.accessToken}")
	private String accessToken;

	@Override
	public void apply(RequestTemplate requestTemplate) {
		requestTemplate.header("X-Buyma-Personal-Shopper-Api-Access-Token", accessToken);
	}

}
