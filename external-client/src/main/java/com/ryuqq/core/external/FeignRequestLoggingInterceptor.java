package com.ryuqq.core.external;

import org.springframework.stereotype.Component;

import com.ryuqq.core.utils.TraceIdHolder;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class FeignRequestLoggingInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {
		template.header("X-Trace-Id", TraceIdHolder.getTraceId());
	}

}
