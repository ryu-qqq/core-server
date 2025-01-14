package com.ryuqq.core.logging;

import com.ryuqq.core.utils.JsonUtils;

/**
 * @param traceId       트레이스 ID
 * @param layer         로깅 대상 레이어
 * @param sql           실행된 SQL 쿼리
 * @param params        바인딩된 파라미터
 * @param executionTime 실행 시간 (ms)
 * @param errorMessage  에러 메시지 (옵션)
 */
public record SqlLogEntry(
	String traceId,
	String layer,
	String sql,
	String params,
	long executionTime,
	String errorMessage // 에러 메시지 필드 추가
) implements LogEntry {
	public String toJson() {
		return JsonUtils.toJson(this);
	}

	@Override
	public String getTraceId() {
		return traceId;
	}

	@Override
	public String getLayer() {
		return layer;
	}
}
