package com.ryuqq.core.api.interceptor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.ryuqq.core.api.filter.RequestWrapper;
import com.ryuqq.core.logging.HttpRequestLogEntry;
import com.ryuqq.core.logging.HttpRequestLogEntryFactory;
import com.ryuqq.core.logging.HttpResponseLogEntry;
import com.ryuqq.core.logging.HttpResponseLogEntryFactory;
import com.ryuqq.core.logging.LogContextManager;
import com.ryuqq.core.logging.LogDispatcher;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LogContextInterceptor implements HandlerInterceptor {

	private static final String HEALTH_CHECKER = "HealthCheckController.fetchHealth";
	private final LogDispatcher logDispatcher;

	public LogContextInterceptor(LogDispatcher logDispatcher) {
		this.logDispatcher = logDispatcher;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		if (handler instanceof HandlerMethod handlerMethod) {
			String controllerName = handlerMethod.getBeanType().getSimpleName();
			String methodName = handlerMethod.getMethod().getName();
			String handlerInfo = controllerName + "." + methodName;

			if (!HEALTH_CHECKER.equals(handlerInfo)) {
				LogContextManager.initialize();

				Map<String, String> headers = getHeaders(request);
				Map<String, String> requestParams = getRequestParameters(request);
				String requestBody = getRequestBody(request);
				String clientIp = getClientIp(request);

				HttpRequestLogEntry requestLogEntry = HttpRequestLogEntryFactory.createHttpRequestLogEntry(
					request.getMethod(),
					request.getRequestURI(),
					headers,
					requestParams,
					requestBody,
					clientIp
				);
				LogContextManager.logToContext(requestLogEntry);
			}
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws IOException {
		if (!request.getRequestURI().equals("/health")) {
			if (response instanceof ContentCachingResponseWrapper wrappedResponse) {
				String responseBody = getResponseBody(wrappedResponse);
				int status = wrappedResponse.getStatus();

				HttpResponseLogEntry responseLogEntry = HttpResponseLogEntryFactory.createHttpResponseLogEntry(status, responseBody);
				LogContextManager.logToContext(responseLogEntry);

				wrappedResponse.copyBodyToResponse();
			}
		}

		logDispatcher.dispatchLogs();
	}

	/** 요청 파라미터를 Map 형태로 반환 */
	private Map<String, String> getRequestParameters(HttpServletRequest request) {
		Map<String, String> params = new HashMap<>();
		if (request instanceof RequestWrapper wrapper) {
			Enumeration<String> parameterNames = wrapper.getParameterNames();
			while (parameterNames.hasMoreElements()) {
				String paramName = parameterNames.nextElement();
				params.put(paramName, wrapper.getParameter(paramName));
			}
		}
		return params;
	}

	/** 요청 본문(Request Body)을 문자열로 반환 */
	private String getRequestBody(HttpServletRequest request) throws IOException {
		if (request instanceof RequestWrapper wrapper) {
			String contentType = wrapper.getContentType();
			boolean visible = isVisible(MediaType.valueOf(contentType == null ? "application/json" : contentType));
			if (visible) {
				byte[] content = wrapper.getContentAsByteArray();
				if (content.length > 0) {
					return new String(content, wrapper.getCharacterEncoding() != null ? wrapper.getCharacterEncoding() : StandardCharsets.UTF_8.name());
				}
			}
			return "";
		}
		return "";
	}

	/** 응답 본문(Response Body)을 문자열로 반환 */
	private String getResponseBody(ContentCachingResponseWrapper response) {
		byte[] content = response.getContentAsByteArray();
		return content.length > 0 ? new String(content, StandardCharsets.UTF_8) : "";
	}

	/** 요청 헤더를 Map 형태로 반환 */
	private Map<String, String> getHeaders(HttpServletRequest request) {
		Map<String, String> headers = new HashMap<>();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			headers.put(headerName, request.getHeader(headerName));
		}
		return headers;
	}

	private boolean isVisible(MediaType mediaType) {
		final List<MediaType> VISIBLE_TYPES = Arrays.asList(
			MediaType.valueOf("text/*"),
			MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON,
			MediaType.APPLICATION_XML,
			MediaType.valueOf("application/*+json"),
			MediaType.valueOf("application/*+xml"),
			MediaType.MULTIPART_FORM_DATA
		);

		return VISIBLE_TYPES.stream().anyMatch(visibleType -> visibleType.includes(mediaType));
	}

	private String getClientIp(HttpServletRequest request) {
		String clientIp = request.getHeader("X-Forwarded-For");
		if (clientIp == null || clientIp.isEmpty()) {
			clientIp = request.getRemoteAddr();
		}
		return clientIp;
	}


}
