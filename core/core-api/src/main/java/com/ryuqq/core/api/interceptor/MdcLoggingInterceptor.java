package com.ryuqq.core.api.interceptor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ryuqq.core.api.filter.RequestWrapper;
import com.ryuqq.core.utils.TraceIdHolder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MdcLoggingInterceptor implements HandlerInterceptor {

	private final Logger log = LoggerFactory.getLogger(getClass());
	private static final String HEALTH_CHECKER = "HealthCheckController.fetchHealth";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		IOException {
		String traceId = TraceIdHolder.getTraceId();

		if (handler instanceof HandlerMethod handlerMethod) {
			String controllerName = handlerMethod.getBeanType().getSimpleName();
			String methodName = handlerMethod.getMethod().getName();
			String handlerInfo = controllerName + "." + methodName;

			if(!HEALTH_CHECKER.equals(handlerInfo)) {
				MDC.put("handler", handlerInfo);

				loggingClientInfo(request);

				Map<String, String> requestParams = getRequestParameters(request);
				log.info("[TraceId: {}] Handler: {}, Request Params: {}", traceId, handlerInfo, requestParams);

				String requestBody = getRequestBody(request);
				if (!requestBody.isEmpty()) {
					log.info("[TraceId: {}] Handler: {}, Request Body: {}", traceId, handlerInfo, requestBody);
				}
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

	private void loggingClientInfo(HttpServletRequest request) {
		if (request instanceof RequestWrapper wrapper) {
			String userAgent = wrapper.getHeader("User-Agent");
			String clientIp = getClientIp(wrapper);
			String traceId = TraceIdHolder.getTraceId();
			String queryString = request.getQueryString();
			String uri = request.getRequestURI() + (queryString != null ? "?" + queryString : "");
			log.info("Request: traceId ={}, method={}, uri={}, headers={}, clientIp={}, userAgent={}",
				traceId,
				request.getMethod(),
				uri,
				getHeaders(wrapper),
				clientIp,
				userAgent
			);

		}

	}

	private String getClientIp(RequestWrapper request) {
		String clientIp = request.getHeader("X-Forwarded-For");
		if (clientIp == null || clientIp.isEmpty()) {
			clientIp = request.getRemoteAddr();
		}
		return clientIp;
	}

	private String getHeaders(RequestWrapper request) {
		StringBuilder headers = new StringBuilder();
		request.getHeaderNames().asIterator().forEachRemaining(headerName ->
			headers.append(headerName).append("=").append(request.getHeader(headerName)).append(", ")
		);
		return headers.toString();
	}



	/**
	 * 요청 파라미터를 Map 형태로 반환
	 */
	private Map<String, String> getRequestParameters(HttpServletRequest request) {
		Map<String, String> params = new HashMap<>();
		if(request instanceof RequestWrapper wrapper) {
			Enumeration<String> parameterNames = wrapper.getParameterNames();
			while (parameterNames.hasMoreElements()) {
				String paramName = parameterNames.nextElement();
				params.put(paramName, wrapper.getParameter(paramName));
			}
		}
		return params;
	}

	/**
	 * 요청 본문(Request Body)을 문자열로 반환
	 */
	private String getRequestBody(HttpServletRequest request) throws IOException {
		if(request instanceof RequestWrapper wrapper) {
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


}
