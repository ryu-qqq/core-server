package com.ryuqq.core.logging;

import java.util.Map;
import java.util.Objects;

public class KibanaLogEntryDto {
	private final String traceId;
	private final String layer;
	private final String className;
	private final String methodName;
	private final Map<String, Object> args;
	private final long executionTime;
	private final String exceptionMessage;
	private final String stackTrace;
	private final String logLevel;
	private final String timestamp;

	public KibanaLogEntryDto(String traceId, String layer, String className, String methodName,
							 Map<String, Object> args, long executionTime,
							 String exceptionMessage, String stackTrace,
							 String logLevel, String timestamp) {
		this.traceId = traceId;
		this.layer = layer;
		this.className = className;
		this.methodName = methodName;
		this.args = args;
		this.executionTime = executionTime;
		this.exceptionMessage = exceptionMessage;
		this.stackTrace = stackTrace;
		this.logLevel = logLevel;
		this.timestamp = timestamp;
	}

	public String getTraceId() { return traceId; }
	public String getLayer() { return layer; }
	public String getClassName() { return className; }
	public String getMethodName() { return methodName; }
	public Map<String, Object> getArgs() { return args; }
	public long getExecutionTime() { return executionTime; }
	public String getExceptionMessage() { return exceptionMessage; }
	public String getStackTrace() { return stackTrace; }
	public String getLogLevel() { return logLevel; }
	public String getTimestamp() { return timestamp; }

	@Override
	public String toString() {
		return String.format(
			"{ \"traceId\": \"%s\", \"layer\": \"%s\", \"className\": \"%s\", \"methodName\": \"%s\", " +
				"\"args\": \"%s\", \"executionTime\": \"%d ms\", \"exceptionMessage\": \"%s\", " +
				"\"stackTrace\": \"%s\", \"logLevel\": \"%s\", \"timestamp\": \"%s\" }",
			traceId, layer, className, methodName, args != null ? args.toString() : "{}",
			executionTime, exceptionMessage != null ? exceptionMessage.replaceAll("\n", " ") : "null",
			stackTrace != null ? stackTrace.replaceAll("\n", " ") : "null",
			logLevel, timestamp
		);
	}

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		KibanaLogEntryDto that = (KibanaLogEntryDto) object;
		return executionTime
			== that.executionTime
			&& Objects.equals(traceId, that.traceId)
			&& Objects.equals(layer, that.layer)
			&& Objects.equals(className, that.className)
			&& Objects.equals(methodName, that.methodName)
			&& Objects.equals(args, that.args)
			&& Objects.equals(exceptionMessage, that.exceptionMessage)
			&& Objects.equals(stackTrace, that.stackTrace)
			&& Objects.equals(logLevel, that.logLevel)
			&& Objects.equals(timestamp, that.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(traceId, layer, className, methodName, args, executionTime, exceptionMessage, stackTrace,
			logLevel, timestamp);
	}
}
