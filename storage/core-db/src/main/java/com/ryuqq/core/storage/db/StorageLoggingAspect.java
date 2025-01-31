package com.ryuqq.core.storage.db;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.ryuqq.core.logging.AopLogEntry;
import com.ryuqq.core.logging.AopLogEntryFactory;
import com.ryuqq.core.logging.LogContextManager;

@Aspect
@Component
public class StorageLoggingAspect {

	private final static String STORAGE_LAYER = "STORAGE";

	@Around("execution(* com.ryuqq.core.storage.db..*(..))")
	public Object logStorageLayer(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();

		AopLogEntry startLogEntry = AopLogEntryFactory.createAopLogEntryWhenStart(joinPoint, STORAGE_LAYER);
		LogContextManager.logToContext(startLogEntry);

		try {
			Object result = joinPoint.proceed();

			AopLogEntry successLogEntry = AopLogEntryFactory.createAopLogEntryWhenSuccess(joinPoint, startTime, STORAGE_LAYER);
			LogContextManager.logToContext(successLogEntry);
			return result;
		} catch (Exception e) {
			AopLogEntry errorLogEntry = AopLogEntryFactory.createAopLogEntryWhenFailed(joinPoint, e, startTime, STORAGE_LAYER);
			LogContextManager.logToContext(errorLogEntry);
			throw e;
		}
	}

}
