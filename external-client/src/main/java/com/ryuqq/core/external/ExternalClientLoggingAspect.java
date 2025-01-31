package com.ryuqq.core.external;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.ryuqq.core.logging.AopLogEntry;
import com.ryuqq.core.logging.AopLogEntryFactory;
import com.ryuqq.core.logging.LogContextManager;

@Aspect
@Component
public class ExternalClientLoggingAspect {

	private final static String EXTERNAL_CLIENT_LAYER = "EXTERNAL_CLIENT";

	@Around("execution(* com.ryuqq.core.external..*(..))")
	public Object logExternalClientLayer(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();

		AopLogEntry startLogEntry = AopLogEntryFactory.createAopLogEntryWhenStart(joinPoint, EXTERNAL_CLIENT_LAYER);
		LogContextManager.logToContext(startLogEntry);

		try {
			Object result = joinPoint.proceed();

			AopLogEntry successLogEntry = AopLogEntryFactory.createAopLogEntryWhenSuccess(joinPoint, startTime, EXTERNAL_CLIENT_LAYER);
			LogContextManager.logToContext(successLogEntry);
			return result;
		} catch (Exception e) {
			AopLogEntry errorLogEntry = AopLogEntryFactory.createAopLogEntryWhenFailed(joinPoint, e, startTime, EXTERNAL_CLIENT_LAYER);
			LogContextManager.logToContext(errorLogEntry);
			throw e;
		}
	}

}
