package com.ryuqq.core.external;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Map;

import org.slf4j.LoggerFactory;

import com.ryuqq.core.enums.LogLevel;
import com.ryuqq.core.logging.ExternalRequestLogEntry;
import com.ryuqq.core.logging.ExternalRequestLogEntryFactory;
import com.ryuqq.core.utils.TraceIdHolder;

import feign.Logger;
import feign.Request;
import feign.Response;


public abstract class AbstractFeignLogger extends Logger {

	protected abstract String getServiceName();

	@Override
	protected void log(String configKey, String format, Object... args) {
		// 기본 Feign 로깅 비활성화
	}

	@Override
	protected void logRequest(String configKey, Level logLevel, Request request) {
		String traceId = TraceIdHolder.getTraceId();
		String requestBody = request.body() != null ? new String(request.body(), StandardCharsets.UTF_8) : "No Body";
		String headers = extractHeaders(request.headers());

		ExternalRequestLogEntry logEntry = ExternalRequestLogEntryFactory.createExternalRequestLogEntry(
			traceId,
			getServiceName(),
			request.url(),
			request.httpMethod().name(),
			requestBody,
			headers,
			0,
			0,
			mapLogLevel(logLevel)
		);

		logMessage(logEntry);
	}

	@Override
	protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime) throws IOException {
		String traceId = TraceIdHolder.getTraceId();
		String responseBody = extractResponseBody(response);
		String headers = extractHeaders(response.headers());

		ExternalRequestLogEntry logEntry = ExternalRequestLogEntryFactory.createExternalRequestLogEntry(
			traceId,
			getServiceName(),
			response.request().url(),
			response.request().httpMethod().name(),
			"",
			headers + "\nBody:\n" + responseBody,
			response.status(),
			elapsedTime,
			mapLogLevel(logLevel)
		);

		logMessage(logEntry);
		return response.toBuilder().body(responseBody.getBytes(StandardCharsets.UTF_8)).build();
	}

	private String extractHeaders(Map<String, Collection<String>> headers) {
		StringBuilder headerBuilder = new StringBuilder();
		headers.forEach((key, values) -> headerBuilder.append(key).append(": ").append(String.join(", ", values)).append("\n"));
		return headerBuilder.toString();
	}

	private String extractResponseBody(Response response) throws IOException {
		if (response.body() == null) {
			return "No Body";
		}
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.body().asInputStream(), StandardCharsets.UTF_8))) {
			return reader.lines().reduce("", (acc, line) -> acc + line);
		}
	}

	private void logMessage(ExternalRequestLogEntry logEntry) {
		switch (logEntry.getLogLevel()) {
			case ERROR -> LoggerFactory.getLogger(getClass()).error("Feign Log: {}", logEntry);
			case WARN -> LoggerFactory.getLogger(getClass()).warn("Feign Log: {}", logEntry);
			case INFO -> LoggerFactory.getLogger(getClass()).info("Feign Log: {}", logEntry);
			case DEBUG -> LoggerFactory.getLogger(getClass()).debug("Feign Log: {}", logEntry);
			case TRACE -> LoggerFactory.getLogger(getClass()).trace("Feign Log: {}", logEntry);
		}
	}

	private LogLevel mapLogLevel(Level feignLogLevel) {
		return switch (feignLogLevel) {
			case NONE -> LogLevel.INFO;
			case BASIC -> LogLevel.INFO;
			case HEADERS -> LogLevel.DEBUG;
			case FULL -> LogLevel.TRACE;
		};
	}
}
