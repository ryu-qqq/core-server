package com.ryuqq.core.external;

import com.ryuqq.core.logging.ExternalRequestLogEntry;
import com.ryuqq.core.utils.TraceIdHolder;

import org.slf4j.LoggerFactory;

import feign.Logger;
import feign.Request;
import feign.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Map;

public class CustomFeignLogger extends Logger {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(CustomFeignLogger.class);

	@Override
	protected void log(String configKey, String format, Object... args) {
	}

	@Override
	protected void logRequest(String configKey, Level logLevel, Request request) {
		String traceId = TraceIdHolder.getTraceId();
		String requestBody = request.body() != null ? new String(request.body(), StandardCharsets.UTF_8) : "No Body";


		StringBuilder headers = new StringBuilder();
		for (Map.Entry<String, Collection<String>> header : request.headers().entrySet()) {
			headers.append(header.getKey()).append(": ").append(String.join(", ", header.getValue())).append("\n");
		}

		ExternalRequestLogEntry logEntry = new ExternalRequestLogEntry(
			traceId,
			"EXTERNAL",
			request.url(),
			request.httpMethod().name(),
			requestBody,
			"Headers:\n" + headers,
			0,
			0
		);

		log.info("Feign Request Log: {}", logEntry.toJson());
	}

	@Override
	protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime) throws IOException {
		String traceId = TraceIdHolder.getTraceId();
		String responseBody = "No Body";

		if (response.body() != null) {
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.body().asInputStream(), StandardCharsets.UTF_8))) {
				responseBody = reader.lines().reduce("", (accumulator, actual) -> accumulator + actual);
			}
		}


		StringBuilder headers = new StringBuilder();
		for (Map.Entry<String, Collection<String>> header : response.headers().entrySet()) {
			headers.append(header.getKey()).append(": ").append(String.join(", ", header.getValue())).append("\n");
		}

		ExternalRequestLogEntry logEntry = new ExternalRequestLogEntry(
			traceId,
			"EXTERNAL",
			response.request().url(),
			response.request().httpMethod().name(),
			"",
			"Headers:\n" + headers + "\nBody:\n" + responseBody,
			response.status(),
			elapsedTime
		);

		log.info("Feign Response Log: {}", logEntry.toJson());

		return response.toBuilder().body(responseBody.getBytes(StandardCharsets.UTF_8)).build();
	}
}
