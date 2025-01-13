package com.ryuqq.core.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

public class ReflectionUtils {

	public static Map<String, Object> getParameterNameValueMap(String className, String methodName, Object[] args) {
		Map<String, Object> argsMap = new HashMap<>();
		try {
			// 클래스와 메서드 정보를 기반으로 Method 객체 획득
			Class<?> clazz = Class.forName(className);
			Method method = findMethodByName(clazz, methodName, args.length);

			if (method != null) {
				Parameter[] parameters = method.getParameters();
				for (int i = 0; i < parameters.length; i++) {
					String paramName = parameters[i].getName();
					argsMap.put(paramName, args[i]);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed to retrieve parameter names for logging", e);
		}
		return argsMap;
	}

	private static Method findMethodByName(Class<?> clazz, String methodName, int paramCount) {
		for (Method method : clazz.getDeclaredMethods()) {
			if (method.getName().equals(methodName) && method.getParameterCount() == paramCount) {
				return method;
			}
		}
		return null;
	}

}
