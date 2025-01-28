package com.ryuqq.core.domain;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.exception.AsyncDomainException;
import com.ryuqq.core.domain.exception.DomainException;
import com.ryuqq.core.enums.ErrorType;
import com.ryuqq.core.events.SlackErrorAlertMessageEvent;
import com.ryuqq.core.logging.LogContext;
import com.ryuqq.core.logging.LogContextManager;
import com.ryuqq.core.logging.QueryLogger;

@Aspect
@Component
public class DomainLoggingAspect  {

	private static final String DOMAIN_LAYER = "DOMAIN";
	private final DomainEventPublisher domainEventPublisher;

	public DomainLoggingAspect(DomainEventPublisher domainEventPublisher) {
		this.domainEventPublisher = domainEventPublisher;
	}

	@Around("execution(* com.ryuqq.core.domain..*(..))")
	public Object logDomainLayer(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		Throwable caughtException = null;

		try {
			return joinPoint.proceed();
		} catch (DomainException e) {
			LogContextManager.logToContext(joinPoint, e, startTime, DOMAIN_LAYER);
			caughtException = e;
			throw e;
		} catch (Exception e) {
			DomainException wrappedException = new DomainException(ErrorType.UNEXPECTED_ERROR, e.getMessage(), e);
			LogContextManager.logToContext(joinPoint, wrappedException, startTime, DOMAIN_LAYER);
			throw wrappedException;
		} finally {
			if (caughtException instanceof AsyncDomainException) {

				LogContext.getLogEntries()
					.stream()
					.filter(entry -> entry.getLogLevel().isLogRequired())
					.forEach(QueryLogger::log);

				String nestedLog = LogContextManager.finalizeAndLog();
				if (nestedLog != null && !nestedLog.isEmpty()) {
					domainEventPublisher.publish(new SlackErrorAlertMessageEvent(nestedLog));
				}
				LogContextManager.clear();
			}
		}
	}

}
