package com.ryuqq.core.domain;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.exception.DomainException;
import com.ryuqq.core.enums.ErrorType;
import com.ryuqq.core.logging.AopLogEntry;
import com.ryuqq.core.logging.LogContext;
import com.ryuqq.core.logging.LogEntryFactory;
import com.ryuqq.core.utils.AopUtils;
import com.ryuqq.core.utils.TraceIdHolder;

@Aspect
@Component
public class DomainLoggingAspect  {


	private static final Logger log = LoggerFactory.getLogger(DomainLoggingAspect.class);
	private static final String DOMAIN_LAYER = "DOMAIN";

	@Pointcut("execution(* com.ryuqq.core.domain..*(..)) ")
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
		} catch ( DomainException e) {
			AopLogEntry aopLogEntry = createLogEntry(traceId, className, methodName, args, e, System.currentTimeMillis() - startTime);
			LogContext.addNestedLogEntry(aopLogEntry);
			throw e;
		} catch (Exception e) {
			DomainException domainException = new DomainException(
				ErrorType.UNEXPECTED_ERROR,
				String.format("Unexpected error in %s.%s", className, methodName),
				e
			);
			AopLogEntry unexpectedErrorLogEntry = createLogEntry(traceId, className, methodName, args, domainException, System.currentTimeMillis() - startTime);
			LogContext.addNestedLogEntry(unexpectedErrorLogEntry);
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


}
