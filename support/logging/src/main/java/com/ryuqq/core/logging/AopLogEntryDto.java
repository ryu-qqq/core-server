package com.ryuqq.core.logging;

import java.util.Map;

public class AopLogEntryDto {
	private static final int MAX_LENGTH = 200; // 최대 길이 설정
	private final String className;
	private final String methodName;
	private final Map<String, Object> args;
	private final long executionTime;
	private final String exceptionMessage;

	public AopLogEntryDto(String className, String methodName, Map<String, Object> args, long executionTime, String exceptionMessage) {
		this.className = className;
		this.methodName = methodName;
		this.args = args;
		this.executionTime = executionTime;
		this.exceptionMessage = exceptionMessage;
	}

	public String getClassName() {
		return className;
	}

	public long getExecutionTime() {
		return executionTime;
	}

	public String getMethodName() {
		return methodName;
	}

	public Map<String, Object> getArgs() {
		return args;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	@Override
	public String toString() {
		return String.format(
			"""
				{
				  "className": "%s",
				  "methodName": "%s",
				  "args": %s,
				  "executionTime": %d ms,
				  "exceptionMessage": %s
				}""",
			truncate(className),
			truncate(methodName),
			truncate(args != null ? args.toString() : "null"),
			executionTime,
			truncate(exceptionMessage)
		);
	}

	private String truncate(String value) {
		if (value == null) return "null";
		return value.length() > MAX_LENGTH ? value.substring(0, MAX_LENGTH) + "..." : value;
	}
}
