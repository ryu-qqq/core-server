package com.ryuqq.core.enums;

public enum ErrorType {

	UNEXPECTED_ERROR(500, ErrorCode.E500, "An unexpected error has occurred.",
		LogLevel.ERROR),

	NOT_FOUND_ERROR(404, ErrorCode.E404, "Resource not found.",
		LogLevel.WARN),

	BAD_REQUEST_ERROR(400, ErrorCode.E400, "Bad Request",
		LogLevel.WARN),

	INVALID_INPUT_ERROR(422, ErrorCode.E422, "Invalid input provided.",
		LogLevel.WARN),

	DATABASE_CONNECTION_ERROR(503, ErrorCode.E503, "Database connection issue.",
		LogLevel.ERROR),

	SLOW_QUERY_DETECTED(200, ErrorCode.E200, "Slow query detected.",
		LogLevel.WARN);

	private final int status;

	private final ErrorCode code;

	private final String message;

	private final LogLevel logLevel;

	ErrorType(int status, ErrorCode code, String message, LogLevel logLevel) {
		this.status = status;
		this.code = code;
		this.message = message;
		this.logLevel = logLevel;
	}


	public static ErrorType of(int status) {
		switch (status) {
			case 400 -> {
				return BAD_REQUEST_ERROR;
			}
			case 404-> {
				return NOT_FOUND_ERROR;
			}
			default -> {
				return UNEXPECTED_ERROR;
			}
		}
	}


	public int getStatus() {
		return status;
	}

	public ErrorCode getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public LogLevel getLogLevel() {
		return logLevel;
	}
}
