package com.ryuqq.core.domain;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class DomainLoggingAspect {

	// private static final String DOMAIN_LAYER = "DOMAIN";
	// private static final long MIN_EXECUTION_TIME_THRESHOLD = 50;
	//
	// @Around("execution(* com.ryuqq.core.domain..*(..))")
	// public Object logDomainLayer(ProceedingJoinPoint joinPoint) throws Throwable {
	// 	long startTime = System.currentTimeMillis();
	//
	// 	try {
	// 		Object result = joinPoint.proceed();
	// 		long executionTime = System.currentTimeMillis() - startTime;
	//
	// 		if (executionTime >= MIN_EXECUTION_TIME_THRESHOLD) {
	// 			AopLogEntry logEntry = AopLogEntryFactory.createAopLogEntryWhenSuccess(joinPoint, executionTime, DOMAIN_LAYER);
	// 			LogContextManager.logToContext(logEntry);
	// 		}
	//
	// 		return result;
	// 	} catch (Exception e) {
	// 		AopLogEntry errorLogEntry = AopLogEntryFactory.createAopLogEntryWhenFailed(joinPoint, e, startTime, DOMAIN_LAYER);
	// 		LogContextManager.logToContext(errorLogEntry);
	// 		throw e;
	// 	}
	// }

}
