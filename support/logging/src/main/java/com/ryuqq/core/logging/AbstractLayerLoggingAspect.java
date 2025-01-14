package com.ryuqq.core.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ryuqq.core.utils.TraceIdHolder;

public abstract class AbstractLayerLoggingAspect {

	private final Logger log = LoggerFactory.getLogger(getClass());

	protected Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String methodName = signature.getName();
		String className = signature.getDeclaringTypeName();
		Object[] args = joinPoint.getArgs();

		String traceId = getTraceId();
		long startTime = System.currentTimeMillis();

		try {
			String preLogMessage = createPreLogMessage(traceId, className, methodName, args);
			log(preLogMessage);

			Object result = joinPoint.proceed();

			long endTime = System.currentTimeMillis();
			String postLogMessage = createPostLogMessage(traceId, className, methodName, args, result, endTime - startTime);
			log(postLogMessage);

			return result;

		} catch (Exception ex) {
			long errorTime = System.currentTimeMillis();
			String errorLogMessage = createErrorLogMessage(traceId, className, methodName, args, ex, errorTime - startTime);
			logError(errorLogMessage, ex);

			throw ex;
		}
	}

	/**
	 * 호출 전 로그 메시지 생성 (구현체에서 정의)
	 */
	protected abstract String createPreLogMessage(String traceId, String className, String methodName, Object[] args);

	/**
	 * 호출 후 로그 메시지 생성 (구현체에서 정의)
	 */
	protected abstract String createPostLogMessage(String traceId, String className, String methodName, Object[] args, Object result, long executionTime);

	/**
	 * 에러 발생 시 로그 메시지 생성 (구현체에서 정의)
	 */
	protected abstract String createErrorLogMessage(String traceId, String className, String methodName, Object[] args, Exception ex, long executionTime);

	/**
	 * 로그 출력 메서드 (구현체에서 정의 가능)
	 */
	protected void log(String message) {
		log.info(message);
	}

	protected void logError(String message, Exception ex) {
		log.error(message, ex);
	}

	private String getTraceId() {
		return TraceIdHolder.getTraceId();
	}

	protected Object extractSafeResult(Object result) {
		if (result == null) {
			return null;
		}

		if (result instanceof Class<?>) {
			return ((Class<?>) result).getSimpleName();
		}

		return result;
	}
}
