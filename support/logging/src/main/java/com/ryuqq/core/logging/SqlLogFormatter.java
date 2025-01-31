package com.ryuqq.core.logging;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SqlLogFormatter {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static String format(SqlLogEntry logEntry) {
		Map<String, Object> logMap = switch (logEntry.getLogLevel()) {
			case ERROR -> formatErrorLog(logEntry);
			case WARN -> formatWarnLog(logEntry);
			default -> formatInfoLog(logEntry);
		};

		try {
			return objectMapper.writeValueAsString(logMap);
		} catch (JsonProcessingException e) {
			return "{ \"error\": \"Failed to serialize log entry\" }";
		}
	}

	private static Map<String, Object> formatErrorLog(SqlLogEntry logEntry) {
		Map<String, Object> logMap = new HashMap<>();
		logMap.put("level", "ERROR");
		logMap.put("traceId", logEntry.getTraceId());
		logMap.put("sql", logEntry.getSql());
		logMap.put("args", truncateArgs(logEntry.getArgs()));
		logMap.put("executionTime", logEntry.getExecutionTime());
		logMap.put("errorMessage", logEntry.getErrorMessage());
		logMap.put("dataSource", logEntry.getDataSourceName());
		logMap.put("connectionId", logEntry.getConnectionId());
		logMap.put("queryPhase", logEntry.getQueryPhase());
		return logMap;
	}

	private static Map<String, Object> formatWarnLog(SqlLogEntry logEntry) {
		Map<String, Object> logMap = new HashMap<>();
		logMap.put("level", "WARN");
		logMap.put("traceId", logEntry.getTraceId());
		logMap.put("sql", logEntry.getSql());
		logMap.put("executionTime", logEntry.getExecutionTime());
		logMap.put("dataSource", logEntry.getDataSourceName());
		logMap.put("connectionId", logEntry.getConnectionId());
		return logMap;
	}

	private static Map<String, Object> formatInfoLog(SqlLogEntry logEntry) {
		Map<String, Object> logMap = new HashMap<>();
		logMap.put("level", "INFO");
		logMap.put("traceId", logEntry.getTraceId());
		logMap.put("sql", truncate(logEntry.getSql()));
		logMap.put("executionTime", logEntry.getExecutionTime());
		return logMap;
	}

	private static String truncate(String data) {
		return (data != null && data.length() > 200) ? data.substring(0, 200) + "..." : data;
	}

	private static Object truncateArgs(Map<String, Object> args) {
		if (args == null) {
			return null;
		}
		String argsJson;
		try {
			argsJson = objectMapper.writeValueAsString(args);
		} catch (JsonProcessingException e) {
			return "{ \"error\": \"Failed to serialize args\" }";
		}
		return argsJson;
	}
}
