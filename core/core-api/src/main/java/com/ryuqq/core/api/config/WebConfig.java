package com.ryuqq.core.api.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ryuqq.core.api.controller.v1.external.resolver.BuyMaEventResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final BuyMaEventResolver buyMaEventResolver;

	public WebConfig(BuyMaEventResolver buyMaEventResolver) {
		this.buyMaEventResolver = buyMaEventResolver;
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(buyMaEventResolver); // ✅ 커스텀 리졸버 등록
	}
}
