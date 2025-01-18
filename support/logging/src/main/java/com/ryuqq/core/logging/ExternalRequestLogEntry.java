package com.ryuqq.core.logging;


/**
 * 외부 요청을 로깅하기 위한 LogEntry
 *
 * @param traceId       트레이스 ID
 * @param layer         로깅 대상 레이어
 * @param url           요청 URL
 * @param httpMethod    HTTP 메서드
 * @param requestBody   요청 바디
 * @param responseBody  응답 바디
 * @param statusCode    HTTP 상태 코드
 * @param executionTime 실행 시간 (ms)
 */
public record ExternalRequestLogEntry(
	String traceId,
	String layer,
	String url,
	String httpMethod,
	String requestBody,
	String responseBody,
	int statusCode,
	long executionTime
) implements LogEntry {

	@Override
	public String getTraceId() {
		return traceId;
	}

	@Override
	public String getLayer() {
		return layer;
	}

	@Override
	public String toString() {
		return "ExternalRequestLogEntry{"
			+
			"traceId='"
			+ traceId
			+ '\''
			+
			", layer='"
			+ layer
			+ '\''
			+
			", url='"
			+ url
			+ '\''
			+
			", httpMethod='"
			+ httpMethod
			+ '\''
			+
			", requestBody='"
			+ requestBody
			+ '\''
			+
			", responseBody='"
			+ responseBody
			+ '\''
			+
			", statusCode="
			+ statusCode
			+
			", executionTime="
			+ executionTime
			+
			'}';
	}
}
