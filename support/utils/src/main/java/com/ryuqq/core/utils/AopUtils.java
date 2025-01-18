package com.ryuqq.core.utils;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

public class AopUtils {

	public static Map<String, Object> extractArgs(ProceedingJoinPoint joinPoint) {
		Map<String, Object> argMap = new HashMap<>();
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String[] parameterNames = signature.getParameterNames();
		Object[] args = joinPoint.getArgs();

		if (parameterNames != null && args != null) {
			for (int i = 0; i < parameterNames.length; i++) {
				argMap.put(parameterNames[i], args[i]);
			}
		}
		return argMap;
	}

}
