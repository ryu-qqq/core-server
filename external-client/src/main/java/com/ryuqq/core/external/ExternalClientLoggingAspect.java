package com.ryuqq.core.external;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ExternalClientLoggingAspect {

	// private final static String EXTERNAL_CLIENT_LAYER = "EXTERNAL_CLIENT";
	//
	// @Around("execution(* com.ryuqq.core.external..*(..))")
	// public Object logExternalClientLayer(ProceedingJoinPoint joinPoint) throws Throwable {
	// 	long startTime = System.currentTimeMillis();
	//
	// 	try {
	// 		return joinPoint.proceed();
	// 	} catch (Exception e) {
	// 		AopLogEntry errorLogEntry = AopLogEntryFactory.createAopLogEntryWhenFailed(joinPoint, e, startTime, EXTERNAL_CLIENT_LAYER);
	// 		LogContextManager.logToContext(errorLogEntry);
	// 		throw e;
	// 	}
	// }

}
