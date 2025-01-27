package com.ryuqq.core.external.oco;

import org.springframework.beans.factory.annotation.Value;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class OcoRequestInterceptor implements RequestInterceptor  {

	@Value("${oco.api-key}")
	private String apiKey;
	private static final String AUTH_ENDPOINT = "/auth/authentication.do";
	private final OcoAuthManager ocoAuthManager;

	public OcoRequestInterceptor(OcoAuthManager ocoAuthManager) {
		this.ocoAuthManager = ocoAuthManager;
	}

	@Override
	public void apply(RequestTemplate template) {
		if (template.url().contains(AUTH_ENDPOINT)) {
			return;
		}

		String token = ocoAuthManager.getToken();
		template.header("token", token);
		template.header("ApiKey", apiKey);
	}


}
