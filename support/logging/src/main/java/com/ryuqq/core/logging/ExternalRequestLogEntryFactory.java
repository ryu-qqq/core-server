package com.ryuqq.core.logging;

import com.ryuqq.core.enums.LogLevel;

public final class ExternalRequestLogEntryFactory {

	public static ExternalRequestLogEntry createExternalRequestLogEntry(
		String traceId, String layer, String url, String httpMethod, String requestBody,
		String responseBody, int statusCode, long executionTime, LogLevel logLevel
	) {
		return new ExternalRequestLogEntry(traceId, layer, url, httpMethod, requestBody, responseBody, statusCode, executionTime, logLevel);
	}

}
