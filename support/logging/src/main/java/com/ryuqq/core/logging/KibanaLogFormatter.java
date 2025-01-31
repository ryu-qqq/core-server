package com.ryuqq.core.logging;

import com.ryuqq.core.utils.TraceIdHolder;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KibanaLogFormatter {

	private static final ObjectMapper objectMapper = new ObjectMapper();


	public static String formatForKibana(List<LogEntry> logEntries) {
		List<KibanaLogEntryDto> entryDtos = logEntries.stream()
			.map(LogEntryConverter::convertToKibanaDto)
			.toList();

		try {
			return objectMapper.writeValueAsString(entryDtos); // ✅ 한 번에 JSON 변환
		} catch (JsonProcessingException e) {
			return "{ \"error\": \"Failed to serialize log entries\" }";
		}
	}


	private static class LogEntryConverter {


		private static KibanaLogEntryDto convertToKibanaDto(LogEntry entry) {
			return switch (entry) {
				case AopLogEntry log -> convertAopLog(log);
				case HttpRequestLogEntry log -> convertHttpRequestLog(log);
				case HttpResponseLogEntry log -> convertHttpResponseLog(log);
				case SqlLogEntry log -> convertSqlLog(log);
				case ExternalRequestLogEntry log -> convertExternalRequestLog(log);
				default -> convertUnknownLog(entry);
			};
		}

		private static KibanaLogEntryDto convertAopLog(AopLogEntry log) {
			return new KibanaLogEntryDto(
				TraceIdHolder.getTraceId(),
				log.getLayer(),
				log.getClassName(),
				log.getMethodName(),
				log.getArgs(),
				log.getExecutionTime(),
				log.getException() != null ? log.getException().getMessage() : null,
				formatStackTrace(log.getStackTrace()),
				log.getLogLevel().name(),
				Instant.now().toString()
			);
		}

		private static KibanaLogEntryDto convertHttpRequestLog(HttpRequestLogEntry log) {
			return new KibanaLogEntryDto(
				TraceIdHolder.getTraceId(),
				log.getLayer(),
				"HttpRequest",
				"Request",
				convertMapToObject(log.getParameters()),
				0,
				null,
				null,
				log.getLogLevel().name(),
				Instant.now().toString()
			);
		}

		private static KibanaLogEntryDto convertHttpResponseLog(HttpResponseLogEntry log) {
			return new KibanaLogEntryDto(
				TraceIdHolder.getTraceId(),
				log.getLayer(),
				"HttpResponse",
				"Response",
				null,
				0,
				null,
				log.getBody(),
				log.getLogLevel().name(),
				Instant.now().toString()
			);
		}

		private static KibanaLogEntryDto convertSqlLog(SqlLogEntry log) {
			return new KibanaLogEntryDto(
				TraceIdHolder.getTraceId(),
				log.getLayer(),
				"SQL",
				"Query",
				log.getArgs(),
				log.getExecutionTime(),
				log.getErrorMessage(),
				null,
				log.getLogLevel().name(),
				Instant.now().toString()
			);
		}

		private static KibanaLogEntryDto convertExternalRequestLog(ExternalRequestLogEntry log) {
			return new KibanaLogEntryDto(
				TraceIdHolder.getTraceId(),
				log.getLayer(),
				"ExternalRequest",
				log.getHttpMethod() + " " + log.getUrl(),
				log.getRequestBody() != null ? Map.of("requestBody", log.getRequestBody()) : null,
				log.getExecutionTime(),
				null,
				log.getResponseBody(),
				log.getLogLevel().name(),
				Instant.now().toString()
			);
		}

		private static KibanaLogEntryDto convertUnknownLog(LogEntry entry) {
			return new KibanaLogEntryDto(
				TraceIdHolder.getTraceId(),
				entry.getLayer(),
				"Unknown",
				"Unknown",
				null,
				0,
				null,
				null,
				entry.getLogLevel().name(),
				Instant.now().toString()
			);
		}


		private static String formatStackTrace(String stackTrace) {
			if (stackTrace == null || stackTrace.isEmpty()) {
				return "No stack trace available.";
			}
			String[] lines = stackTrace.split("\n");
			return String.join("\n", List.of(lines).subList(0, Math.min(lines.length, 3)));
		}


		private static Map<String, Object> convertMapToObject(Map<String, String> stringMap) {
			return Optional.ofNullable(stringMap)
				.map(map -> map.entrySet().stream()
					.collect(Collectors.toMap(Map.Entry::getKey, e -> (Object) e.getValue())))
				.orElse(null);
		}
	}

}
