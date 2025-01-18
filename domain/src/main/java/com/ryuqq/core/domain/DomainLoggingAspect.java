package com.ryuqq.core.domain;

import com.ryuqq.core.domain.exception.DomainException;
import com.ryuqq.core.logging.AopLogEntry;
import com.ryuqq.core.logging.LogEntryFactory;
import com.ryuqq.core.storage.db.exception.RdsStorageException;
import com.ryuqq.core.utils.AopUtils;
import com.ryuqq.core.utils.TraceIdHolder;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DomainLoggingAspect  {

	private final DomainErrorSlackNotificationService slackNotificationService;

	private static final Logger log = LoggerFactory.getLogger(DomainLoggingAspect.class);
	private static final String DOMAIN_LAYER = "DOMAIN";

	public DomainLoggingAspect(DomainErrorSlackNotificationService slackNotificationService) {
		this.slackNotificationService = slackNotificationService;
	}

	@Pointcut("execution(* com.ryuqq.core.domain..*(..)) && !within(com.ryuqq.core.domain.DomainErrorSlackNotificationService)")
	public void domainLayerMethods() {}

	@Around("domainLayerMethods()")
	public Object logDomainLayer(ProceedingJoinPoint joinPoint) throws Throwable {
		String traceId = TraceIdHolder.getTraceId();
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		Map<String, Object> args = AopUtils.extractArgs(joinPoint);
		long startTime = System.currentTimeMillis();

		try {
			return joinPoint.proceed();
		} catch (RdsStorageException e) {
			throw e;

		}catch (DomainException e) {
			logError(createLogEntry(traceId, className, methodName, args, e, System.currentTimeMillis() - startTime));
			throw e;

		} catch (Exception e) {
			DomainException domainException = new DomainException(
				String.format("Unexpected error in %s.%s", className, methodName),
				e
			);
			logError(createLogEntry(traceId, className, methodName, args, domainException, System.currentTimeMillis() - startTime));
			throw domainException;
		}
	}

	private AopLogEntry createLogEntry(String traceId, String className, String methodName, Map<String, Object> args, Throwable exception, long executionTime) {
		return LogEntryFactory.createAopLogEntry(
			traceId,
			DOMAIN_LAYER,
			className,
			methodName,
			args,
			exception,
			executionTime
		);
	}

	private void logError(AopLogEntry logEntry) {
		log.error(logEntry.toString(), logEntry.exception());
		slackNotificationService.sendErrorAlert(logEntry);
	}


}
