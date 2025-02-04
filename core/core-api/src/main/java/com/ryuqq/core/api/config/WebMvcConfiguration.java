package com.ryuqq.core.api.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	// private final MdcLoggingInterceptor mdcLoggingInterceptor;
	// private final LogContextInterceptor logContextInterceptor;
	//
	// public WebMvcConfiguration(MdcLoggingInterceptor mdcLoggingInterceptor, LogContextInterceptor logContextInterceptor) {
	// 	this.mdcLoggingInterceptor = mdcLoggingInterceptor;
	// 	this.logContextInterceptor = logContextInterceptor;
	// }
	//
	// @Override
	// public void addInterceptors(InterceptorRegistry registry) {
	// 	registry.addInterceptor(mdcLoggingInterceptor);
	// 	registry.addInterceptor(logContextInterceptor);
	// }
	//
	// @Override
	// public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
	// 	resolvers.add(new GitHubEventResolver());
	// 	resolvers.add(new BuyMaEventResolver());
	// }

}
