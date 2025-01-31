package com.ryuqq.core.logging;

import java.util.Map;

import com.ryuqq.core.enums.LogLevel;

public class AopLogEntryFactory {

	public static AopLogEntry createAopLogEntry(
		String traceId,
		String layer,
		String className,
		String methodName,
		Map<String, Object> args,
		Throwable exception,
		long executionTime,
		LogLevel logLevel,
		String callerData

	) {
		return new AopLogEntry(traceId, layer, className, methodName, args, exception,
			executionTime, logLevel, callerData);
	}

}
