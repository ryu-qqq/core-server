package com.ryuqq.core.logging;

import java.util.Map;

import com.ryuqq.core.utils.ToStringUtils;

/**
 * AOP 로그 엔트리 구현체
 *
 * @param traceId         요청의 고유 식별자 (Trace ID)
 * @param layer           로깅 대상 레이어 (예: STORAGE, SERVICE 등)
 * @param className       호출된 클래스 이름
 * @param methodName      호출된 메서드 이름
 * @param args            메서드에 전달된 파라미터 배열
 * @param exception       발생한 예외 (옵션)
 * @param executionTime   메서드 실행 시간 (밀리초)
 */
public record AopLogEntry(
	String traceId,
	String layer,
	String className,
	String methodName,
	Map<String, Object> args,
	Throwable exception,
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
		return "- Trace ID: " + traceId + "\n" +
			"- Layer: " + layer + "\n" +
			"- Class: " + className + "\n" +
			"- Method: " + methodName + "\n" +
			"- Error: " + (exception != null ? exception.getMessage() : "None") + "\n" +
			"- Arguments: " + ToStringUtils.formatParams(args) + "\n";
	}

}
