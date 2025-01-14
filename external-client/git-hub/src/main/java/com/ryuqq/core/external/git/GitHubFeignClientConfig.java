package com.ryuqq.core.external.git;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
public class GitHubFeignClientConfig  {

	private final GitHubApiProperties properties;


	private static final String GIT_HUB_AUTHORIZATION_HEADER = "Authorization ";
	private static final String GIT_HUB_AUTHORIZATION_HEADER_PREFIX = "Bearer ";

	public GitHubFeignClientConfig(GitHubApiProperties properties) {
		this.properties = properties;
	}

	@Bean
	public RequestInterceptor gitHubRequestInterceptor() {
		return new RequestInterceptor() {
			@Override
			public void apply(RequestTemplate requestTemplate) {
				requestTemplate.header(GIT_HUB_AUTHORIZATION_HEADER, GIT_HUB_AUTHORIZATION_HEADER_PREFIX + properties.getToken());
			}
		};
	}

}
