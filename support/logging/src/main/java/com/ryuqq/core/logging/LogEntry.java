package com.ryuqq.core.logging;

import com.ryuqq.core.utils.JsonUtils;

import java.util.Map;

/**
 * LogEntry 객체를 생성하는 팩토리 메서드.
 *
 * @param traceId       요청의 고유 식별자 (Trace ID)
 * @param layer         로깅 대상 레이어 (예: DOMAIN, SERVICE 등)
 * @param className     호출된 클래스 이름
 * @param methodName    호출된 메서드 이름
 * @param args          메서드에 전달된 파라미터 배열
 * @param result        메서드 실행 결과
 * @param executionTime 메서드 실행 시간 (밀리초)
 */

public record LogEntry(
	String traceId,          // 트레이스 ID
	String layer,            // 레이어 (예: DOMAIN, SERVICE 등)
	String className,        // 클래스 이름
	String methodName,       // 메서드 이름
	Map<String, Object> args, // 메서드 파라미터
	Object result,           // 실행 결과
	long executionTime       // 실행 시간 (ms)
) {

	public String toJson(){
		return JsonUtils.toJson(this);
	}
}
