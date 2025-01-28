package com.ryuqq.core.external;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.ryuqq.core.enums.ErrorType;
import com.ryuqq.core.logging.LogContextManager;

@Aspect
@Component
public class ExternalClientLoggingAspect {

	private final static String EXTERNAL_CLIENT_LAYER = "EXTERNAL_CLIENT";

	@Around("execution(* com.ryuqq.core.external..*(..))")
	public Object handleStorageLayerExceptions(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();

		try {
			return joinPoint.proceed();
		} catch (ExternalSiteException e) {
			LogContextManager.logToContext(joinPoint, e, startTime, EXTERNAL_CLIENT_LAYER);
			throw e;
		} catch (Exception e) {
			ExternalSiteException wrappedException = new ExternalSiteException(ErrorType.UNEXPECTED_ERROR, "Unexpected error occurred External Site", e);
			LogContextManager.logToContext(joinPoint, wrappedException, startTime, EXTERNAL_CLIENT_LAYER);
			throw wrappedException;
		}
	}


}
