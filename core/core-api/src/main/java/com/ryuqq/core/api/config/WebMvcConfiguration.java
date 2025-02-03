package com.ryuqq.core.api.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ryuqq.core.api.controller.v1.external.resolver.BuyMaEventResolver;
import com.ryuqq.core.api.controller.v1.git.resolver.GitHubEventResolver;
import com.ryuqq.core.api.interceptor.LogContextInterceptor;
import com.ryuqq.core.api.interceptor.MdcLoggingInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	private final MdcLoggingInterceptor mdcLoggingInterceptor;
	private final LogContextInterceptor logContextInterceptor;

	public WebMvcConfiguration(MdcLoggingInterceptor mdcLoggingInterceptor, LogContextInterceptor logContextInterceptor) {
		this.mdcLoggingInterceptor = mdcLoggingInterceptor;
		this.logContextInterceptor = logContextInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(mdcLoggingInterceptor);
		registry.addInterceptor(logContextInterceptor);
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new GitHubEventResolver());
		resolvers.add(new BuyMaEventResolver());
	}

}
