package com.ryuqq.core.storage.db;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class StorageLoggingAspect {

	private final static String STORAGE_LAYER = "STORAGE";

	// @Around("execution(* com.ryuqq.core.storage.db..*(..))")
	// public Object logStorageLayer(ProceedingJoinPoint joinPoint) throws Throwable {
	// 	long startTime = System.currentTimeMillis();
	//
	// 	try {
	// 		return joinPoint.proceed();
	// 	} catch (Exception e) {
	// 		AopLogEntry errorLogEntry = AopLogEntryFactory.createAopLogEntryWhenFailed(joinPoint, e, startTime, STORAGE_LAYER);
	// 		LogContextManager.logToContext(errorLogEntry);
	// 		throw e;
	// 	}
	// }

}
