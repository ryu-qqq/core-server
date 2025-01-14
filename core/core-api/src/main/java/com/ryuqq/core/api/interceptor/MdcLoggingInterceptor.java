package com.ryuqq.core.api.interceptor;

import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;

import com.ryuqq.core.utils.TraceIdHolder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MdcLoggingInterceptor implements HandlerInterceptor {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String traceId = TraceIdHolder.getTraceId();

		if (handler instanceof HandlerMethod handlerMethod) {
			String controllerName = handlerMethod.getBeanType().getSimpleName();
			String methodName = handlerMethod.getMethod().getName();
			String handlerInfo = controllerName
				+ "."
				+ methodName;

			MDC.put("handler", handlerInfo);

			Map<String, String> requestParams = getRequestParameters(request);
			log.info("[TraceId: {}] Handler: {}, Request Params: {}", traceId, handlerInfo, requestParams);

			String requestBody = getRequestBody(request);
			if (!requestBody.isEmpty()) {
				log.info("[TraceId: {}] Handler: {}, Request Body: {}", traceId, handlerInfo, requestBody);
			}
		}

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
								Exception ex) throws Exception {
		String traceId = TraceIdHolder.getTraceId();

		log.info("[TraceId: {}] Completed Request: {}, Status: {}", traceId, request.getRequestURI(),
			response.getStatus());
		if (ex
			!= null) {
			log.error("[TraceId: {}] Exception: {}", traceId, ex.getMessage(), ex);
		}

	}



	/**
	 * 요청 파라미터를 Map 형태로 반환
	 */
	private Map<String, String> getRequestParameters(HttpServletRequest request) {
		Map<String, String> params = new HashMap<>();
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			params.put(paramName, request.getParameter(paramName));
		}
		return params;
	}

	/**
	 * 요청 본문(Request Body)을 문자열로 반환
	 */
	private String getRequestBody(HttpServletRequest request) {
		if(request instanceof ContentCachingRequestWrapper) {
			ContentCachingRequestWrapper wrapper = (ContentCachingRequestWrapper) request;
			byte[] content = wrapper.getContentAsByteArray();
			return new String(content, StandardCharsets.UTF_8); // UTF-8로 변환
		}
		return "";
	}

}
