package com.ryuqq.core.api;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ApiLoggingAspect {

	// private final static String API_LAYER = "API";
	//
	// @Around("execution(* com.ryuqq.core.api.controller.v1..*(..)) ")
	// public Object logDomainLayer(ProceedingJoinPoint joinPoint) throws Throwable {
	// 	long startTime = System.currentTimeMillis();
	//
	// 	try {
	// 		return joinPoint.proceed();
	// 	} catch (Exception e) {
	// 		AopLogEntry errorLogEntry = AopLogEntryFactory.createAopLogEntryWhenFailed(joinPoint, e, startTime, API_LAYER);
	// 		LogContextManager.logToContext(errorLogEntry);
	// 		throw e;
	// 	}
	// }


}
