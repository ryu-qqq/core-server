package com.ryuqq.core.logging;

import java.io.StringReader;
import java.util.Map;

import com.ryuqq.core.utils.ReflectionUtils;

public class LogEntryFactory {

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
	 * @return LogEntry 객체
	 */

	public static LogEntry createLogEntry(String traceId, String layer, String className, String methodName, Object[] args, Object result, long executionTime) {
		Map<String, Object> argsMap = ReflectionUtils.getParameterNameValueMap(className, methodName, args);

		return new LogEntry(
			traceId,
			layer,
			className,
			methodName,
			argsMap,
			result,
			executionTime
		);
	}

	/**
	 *
	 * @param traceId 요청의 고유 식별자 (Trace ID)
	 * @param layer 로깅 대상 레이어 (예: STORAGE )
	 * @param sql 실행된 sql
	 * @param params 쿼리에 사용된 파라미터
	 * @param executionTime 쿼리 실행 시간 (밀리초)
	 * @return SqlLogEntry 객체
	 */

	public static SqlLogEntry createSqlLogEntry(String traceId, String layer, String sql, Map<Object, Object> params, long executionTime) {
		return new SqlLogEntry(
			traceId,
			layer,
			sql,
			transformParams(params),
			executionTime,
			""
		);
	}


	public static SqlLogEntry createSqlLogEntryWithError(String traceId, String layer, String sql, Map<Object, Object> params, long executionTime, String errorMessage) {
		return new SqlLogEntry(
			traceId,
			layer,
			sql,
			transformParams(params),
			executionTime,
			errorMessage
		);
	}


	private static String transformParams(Map<Object, Object> originalParams) {
		StringBuilder sb = new StringBuilder("{\n");
		originalParams.forEach((key, value) ->
			sb.append("  ")
				.append(key)
				.append(": ")
				.append(value instanceof StringReader ? "StringReader" : value)
				.append("\n"));
		sb.append("}");
		return sb.toString();
	}

}
