package com.ryuqq.core.storage.db;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.ryuqq.core.enums.ErrorType;
import com.ryuqq.core.logging.LogContextManager;
import com.ryuqq.core.storage.db.exception.RdsStorageException;

@Aspect
@Component
public class StorageLoggingAspect {

	private final static String STORAGE_LAYER = "STORAGE";

	@Around("execution(* com.ryuqq.core.storage.db..*(..))")
	public Object handleStorageLayerExceptions(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();

		try {
			return joinPoint.proceed();
		} catch (RdsStorageException e) {
			LogContextManager.logToContext(joinPoint, e, startTime, STORAGE_LAYER);
			throw e;
		} catch (Exception e) {
			RdsStorageException wrappedException = new RdsStorageException(ErrorType.UNEXPECTED_ERROR, "Unexpected error occurred", e);
			LogContextManager.logToContext(joinPoint, wrappedException, startTime, STORAGE_LAYER);
			throw wrappedException;
		}
	}


}
