package com.ryuqq.core.api.interceptor;

import java.io.IOException;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ryuqq.core.utils.TraceIdHolder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class MdcLoggingInterceptor implements HandlerInterceptor {

	private static final String HEALTH_CHECKER = "HealthCheckController.fetchHealth";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		if (handler instanceof HandlerMethod handlerMethod) {
			String controllerName = handlerMethod.getBeanType().getSimpleName();
			String methodName = handlerMethod.getMethod().getName();
			String handlerInfo = controllerName + "." + methodName;

			if (!HEALTH_CHECKER.equals(handlerInfo)) {
				MDC.put("traceId", TraceIdHolder.getTraceId());
				MDC.put("handler", handlerInfo);
			}
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		MDC.clear();
	}

}
