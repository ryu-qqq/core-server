package com.ryuqq.core.utils;

import org.slf4j.MDC;

public class TraceIdHolder {

	public static String getTraceId(){
		String traceId = MDC.get("traceId");
		if (traceId == null) {
			traceId = "N/A";
		}
		return traceId;
	}
}
