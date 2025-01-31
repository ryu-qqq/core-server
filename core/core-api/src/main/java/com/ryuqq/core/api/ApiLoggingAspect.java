package com.ryuqq.core.api;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.ryuqq.core.logging.AopLogEntry;
import com.ryuqq.core.logging.AopLogEntryFactory;
import com.ryuqq.core.logging.LogContextManager;

@Aspect
@Component
public class ApiLoggingAspect {

	private final static String API_LAYER = "API";

	@Around("execution(* com.ryuqq.core.api.controller.v1..*(..)) ")
	public Object logDomainLayer(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();

		AopLogEntry startLogEntry = AopLogEntryFactory.createAopLogEntryWhenStart(joinPoint, API_LAYER);
		LogContextManager.logToContext(startLogEntry);

		try {
			Object result = joinPoint.proceed();
			AopLogEntry successLogEntry = AopLogEntryFactory.createAopLogEntryWhenSuccess(joinPoint, startTime, API_LAYER);
			LogContextManager.logToContext(successLogEntry);
			return result;
		} catch (Exception e) {
			AopLogEntry errorLogEntry = AopLogEntryFactory.createAopLogEntryWhenFailed(joinPoint, e, startTime, API_LAYER);
			LogContextManager.logToContext(errorLogEntry);
			throw e;
		}
	}


}
