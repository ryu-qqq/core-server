package com.ryuqq.core.logging;

import java.util.Map;

public class HttpRequestLogEntryFactory {

	public static HttpRequestLogEntry createHttpRequestLogEntry(String httpMethod, String requestUri, Map<String, String> headers, Map<String, String> parameters, String requestBody, String clientIp) {
		return new HttpRequestLogEntry(
			httpMethod, requestUri, headers, parameters, requestBody, clientIp
		);
	}
}
