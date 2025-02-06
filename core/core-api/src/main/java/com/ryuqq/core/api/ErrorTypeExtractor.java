package com.ryuqq.core.api;

import java.lang.reflect.Field;

import com.ryuqq.core.enums.ErrorType;

public class ErrorTypeExtractor {

	/**
	 * 예외 객체 내부에서 ErrorType 필드를 추출하는 메서드
	 *
	 * @param exception 확인할 예외 객체
	 * @return ErrorType 값 (존재하면 반환, 없으면 null)
	 */
	public static ErrorType extractErrorType(Throwable exception) {
		if (exception == null) {
			return null;
		}

		try {
			Field field = exception.getClass().getDeclaredField("errorType");
			field.setAccessible(true);  // private 필드 접근 허용

			Object value = field.get(exception);
			if (value instanceof ErrorType) {
				return (ErrorType) value;
			}
		} catch (NoSuchFieldException | IllegalAccessException e) {
			return null;
		}

		return null;
	}

}
