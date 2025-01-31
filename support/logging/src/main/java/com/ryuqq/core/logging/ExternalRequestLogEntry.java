package com.ryuqq.core.logging;

import java.util.Objects;

import com.ryuqq.core.enums.LogLevel;

/**
 * 외부 요청을 로깅하기 위한 LogEntry
 */

public final class ExternalRequestLogEntry extends AbstractLogEntry {
	private final String url;
	private final String httpMethod;
	private final String requestBody;
	private final String responseBody;
	private final int statusCode;
	private final long executionTime;
	private final LogLevel logLevel;

	public ExternalRequestLogEntry(String traceId, String layer, String url, String httpMethod, String requestBody,
								   String responseBody, int statusCode, long executionTime, LogLevel logLevel) {
		super(traceId, layer);
		this.url = url;
		this.httpMethod = httpMethod;
		this.requestBody = requestBody;
		this.responseBody = responseBody;
		this.statusCode = statusCode;
		this.executionTime = executionTime;
		this.logLevel = logLevel;
	}

	public String getUrl() {
		return url;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public long getExecutionTime() {
		return executionTime;
	}

	@Override
	public LogLevel getLogLevel() {
		return logLevel;
	}

	@Override
	public String toString() {
		return super.toString() +
			"- URL: " + url + "\n" +
			"- HTTP Method: " + httpMethod + "\n" +
			"- Request Body: " + requestBody + "\n" +
			"- Response Body: " + responseBody + "\n" +
			"- Status Code: " + statusCode + "\n" +
			"- Execution Time: " + executionTime + "ms\n";
	}

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		ExternalRequestLogEntry that = (ExternalRequestLogEntry) object;
		return statusCode
			== that.statusCode
			&& executionTime
			== that.executionTime
			&& Objects.equals(url, that.url)
			&& Objects.equals(httpMethod, that.httpMethod)
			&& Objects.equals(requestBody, that.requestBody)
			&& Objects.equals(responseBody, that.responseBody)
			&& logLevel
			== that.logLevel;
	}

	@Override
	public int hashCode() {
		return Objects.hash(url, httpMethod, requestBody, responseBody, statusCode, executionTime, logLevel);
	}
}
