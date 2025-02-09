package com.ryuqq.core.domain;

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
			Class<?> clazz = exception.getClass();
			while (clazz != null) {
				try {
					Field field = clazz.getDeclaredField("errorType");
					field.setAccessible(true);  // private 필드 접근 허용

					Object value = field.get(exception);
					if (value instanceof ErrorType) {
						return (ErrorType) value;
					}
					return null;
				} catch (NoSuchFieldException ignored) {
					// 부모 클래스로 이동
					clazz = clazz.getSuperclass();
				}
			}
		} catch (IllegalAccessException e) {
			return null;
		}

		return null;
	}


}
