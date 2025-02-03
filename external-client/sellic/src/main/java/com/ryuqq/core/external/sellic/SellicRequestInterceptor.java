package com.ryuqq.core.external.sellic;

import org.springframework.beans.factory.annotation.Value;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class SellicRequestInterceptor implements RequestInterceptor {

	@Value("${sellic.customer_id}")
	private String customerId;

	@Value("${sellic.api_key}")
	private String apiKey;

	@Override
	public void apply(RequestTemplate requestTemplate) {
		if (requestTemplate.body() != null) {
			String body = new String(requestTemplate.body());
			String updatedBody = addAuthFieldsToBody(body, customerId, apiKey);
			requestTemplate.body(updatedBody);
		}
	}

	private String addAuthFieldsToBody(String body, String customerId, String apiKey) {
		return body.replaceFirst("\\{", String.format("{\"customer_id\":\"%s\",\"api_key\":\"%s\",", customerId, apiKey));
	}


}
