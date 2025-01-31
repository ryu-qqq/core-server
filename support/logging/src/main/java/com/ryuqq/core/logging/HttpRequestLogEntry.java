package com.ryuqq.core.logging;

import java.util.Map;

import com.ryuqq.core.enums.LogLevel;
import com.ryuqq.core.utils.TraceIdHolder;

public class HttpRequestLogEntry implements LogEntry {
	private final String traceId;
	private final String layer;
	private final String httpMethod;
	private final String requestUri;
	private final Map<String, String> headers;
	private final Map<String, String> parameters;
	private final String requestBody;
	private final String clientIp;

	protected HttpRequestLogEntry(String httpMethod, String requestUri, Map<String, String> headers, Map<String, String> parameters, String requestBody, String clientIp) {
		this.traceId = TraceIdHolder.getTraceId();
		this.layer = "HTTP";
		this.httpMethod = httpMethod;
		this.requestUri = requestUri;
		this.headers = headers;
		this.parameters = parameters;
		this.requestBody = requestBody;
		this.clientIp = clientIp;
	}

	@Override
	public String getTraceId() {
		return traceId;
	}

	@Override
	public String getLayer() {
		return layer;
	}

	@Override
	public LogLevel getLogLevel() {
		return LogLevel.INFO;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public String getRequestUri() {
		return requestUri;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public String getClientIp() {
		return clientIp;
	}

	@Override
	public String toString() {
		return String.format(
			"""
			[HTTP REQUEST] TraceId: %s
			- Method: %s
			- URI: %s
			- Client IP: %s
			- Headers: %s
			- Parameters: %s
			- Body: %s
			""",
			traceId, httpMethod, requestUri, clientIp, headers, parameters, requestBody
		);
	}
}
