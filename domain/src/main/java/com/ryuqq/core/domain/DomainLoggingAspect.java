package com.ryuqq.core.domain;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.exception.DataNotFoundException;
import com.ryuqq.core.domain.exception.DomainException;
import com.ryuqq.core.logging.AopLogEntry;
import com.ryuqq.core.logging.AopLogEntryFactory;
import com.ryuqq.core.logging.LogContextManager;

@Aspect
@Component
public class DomainLoggingAspect {

	private static final String DOMAIN_LAYER = "DOMAIN";

	@Around("execution(* com.ryuqq.core.domain..*(..))")
	public Object logDomainLayer(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();

		try {
			return joinPoint.proceed();
		} catch (Exception e) {
			AopLogEntry errorLogEntry = AopLogEntryFactory.createAopLogEntryWhenFailed(joinPoint, e, startTime, DOMAIN_LAYER);
			LogContextManager.logToContext(errorLogEntry);
			throw e;
		}
	}

}
