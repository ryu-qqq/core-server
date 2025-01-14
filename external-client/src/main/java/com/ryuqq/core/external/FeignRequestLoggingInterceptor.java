package com.ryuqq.core.external;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import com.ryuqq.core.utils.TraceIdHolder;

import org.springframework.stereotype.Component;

@Component
public class FeignRequestLoggingInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {
		template.header("X-Trace-Id", TraceIdHolder.getTraceId());
	}

}
