package com.ryuqq.core.alert;

import java.util.Map;

public record AlertMessage(
	String title,
	String traceId,
	String layer,
	String className,
	String methodName,
	String errorMessage,
	Map<String, Object> args
) {

	public String formatForSlack() {
		return String.format(
			"""
			*Error Report Summary*
			-------------------------------
			*Trace ID*: %s
			*Layer*: %s
			*Class*: %s
			*Method*: %s
			*Error*: %s
			*Arguments*: %s
			-------------------------------
			""",
			title, traceId, layer, className, methodName, errorMessage, args
		);
	}

}
