package com.ryuqq.core.external;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Map;

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



	}

	@Override
	protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime) throws IOException {
		String traceId = TraceIdHolder.getTraceId();
		String responseBody = extractResponseBody(response);
		String headers = extractHeaders(response.headers());


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


}
