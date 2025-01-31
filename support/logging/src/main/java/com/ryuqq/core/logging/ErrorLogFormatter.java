package com.ryuqq.core.logging;

public class ErrorLogFormatter {

	public static String formatErrorAopLogEntry(AopLogEntry logEntry) {
		return String.format(
			"""
			🔥 [ERROR] Class: %s, Method: %s
			🕒 ExecutionTime: %d ms
			📌 CallerData: %s
			🖥️ Host: %s | Process: %s | App: %s
			🛑 Exception: %s
			📜 StackTrace:
			%s
			""",
			logEntry.getClassName(),
			logEntry.getMethodName(),
			logEntry.getExecutionTime(),
			logEntry.getCallerData(),
			logEntry.getHostName(),
			logEntry.getProcessId(),
			logEntry.getApplicationName(),
			logEntry.getException() != null ? logEntry.getException().getMessage() : "No Exception",
			logEntry.getStackTrace() != null ? logEntry.getStackTrace() : "No StackTrace"
		);
	}
}
