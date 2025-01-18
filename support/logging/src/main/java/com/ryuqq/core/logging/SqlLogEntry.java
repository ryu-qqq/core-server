package com.ryuqq.core.logging;

import com.ryuqq.core.utils.ToStringUtils;

import java.util.Map;

import org.springframework.boot.logging.LogLevel;
/**
 * SQL 로그 엔트리 구현체
 *
 * @param traceId         트레이스 ID
 * @param layer           로깅 대상 레이어
 * @param sql             실행된 SQL 쿼리
 * @param className		  클래스 이름
 * @param methodName	  메서드 이름
 * @param args          바인딩된 파라미터
 * @param executionTime   실행 시간 (ms)
 * @param errorMessage    에러 메시지 (옵션)
 * @param logLevel        로그 레벨 (예: INFO, WARN, ERROR)
 * @param dataSourceName  데이터 소스 이름
 * @param connectionId    연결 ID
 * @param isolationLevel  트랜잭션 격리 수준
 * @param queryPhase      쿼리 실행 단계 (BEFORE, AFTER)
 */
public record SqlLogEntry(
	String traceId,
	String layer,
	String sql,
	Map<String, Object> args,
	long executionTime,
	String errorMessage,
	LogLevel logLevel,
	String dataSourceName,
	String connectionId,
	int isolationLevel,
	String queryPhase
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
		return "SqlLogEntry{" +
			"traceId='" + traceId + '\'' +
			", layer='" + layer + '\'' +
			", sql='" + sql + '\'' +
			", args=" + ToStringUtils.formatParams(args) +
			", executionTime=" + executionTime +
			", errorMessage='" + errorMessage + '\'' +
			", logLevel=" + logLevel +
			", dataSourceName='" + dataSourceName + '\'' +
			", connectionId='" + connectionId + '\'' +
			", isolationLevel=" + isolationLevel +
			", queryPhase='" + queryPhase + '\'' +
			'}';
	}

}
