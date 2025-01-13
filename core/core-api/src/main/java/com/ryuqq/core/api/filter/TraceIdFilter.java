package com.ryuqq.core.api.filter;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TraceIdFilter extends OncePerRequestFilter {

	private static final String TRACE_ID_HEADER = "X-Trace-Id";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
									FilterChain filterChain) throws ServletException, IOException {
		String traceId = request.getHeader(TRACE_ID_HEADER);
		if (traceId == null || traceId.isEmpty()) {
			traceId = UUID.randomUUID().toString();
		}

		MDC.put("traceId", traceId);

		response.setHeader(TRACE_ID_HEADER, traceId);
		ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);

		try {
			filterChain.doFilter(wrappedRequest, response); // 다음 필터 또는 서블릿 실행
		} finally {
			MDC.clear(); // 요청 처리 후 MDC 정리
		}
	}
}
