package com.ryuqq.core.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ryuqq.core.api.controller.v1.git.resolver.GitHubEventResolver;
import com.ryuqq.core.api.interceptor.MdcLoggingInterceptor;

import java.util.List;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MdcLoggingInterceptor());
	}


	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new GitHubEventResolver());
	}

}
