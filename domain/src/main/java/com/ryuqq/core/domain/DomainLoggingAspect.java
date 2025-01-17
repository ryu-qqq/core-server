package com.ryuqq.core.domain;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.ryuqq.core.logging.AbstractLayerLoggingAspect;
import com.ryuqq.core.logging.LogEntryFactory;
import com.ryuqq.core.logging.SimpleLogEntry;

@Aspect
@Component
public class DomainLoggingAspect extends AbstractLayerLoggingAspect  {

	private static final String DOMAIN_LAYER = "DOMAIN";

	@Pointcut("execution(* com.ryuqq.core.domain..*(..))")
	public void domainLayerMethods() {
	}

	@Around("domainLayerMethods()")
	public Object logDomainLayer(ProceedingJoinPoint joinPoint) throws Throwable {
		return logExecution(joinPoint);
	}

	@Override
	protected String createPreLogMessage(String traceId, String className, String methodName, Object[] args) {
		SimpleLogEntry simpleLogEntry = LogEntryFactory.createLogEntry(
			traceId,
			DOMAIN_LAYER,
			className,
			methodName,
			args,
			null,
			0L
		);

		return simpleLogEntry.toJson();
	}

	@Override
	protected String createPostLogMessage(String traceId, String className, String methodName, Object[] args,
										  Object result, long executionTime) {

		Object safeResult = extractSafeResult(result);

		SimpleLogEntry simpleLogEntry = LogEntryFactory.createLogEntry(
			traceId,
			DOMAIN_LAYER,
			className,
			methodName,
			args,
			safeResult,
			executionTime
		);

		return simpleLogEntry.toJson();
	}

}
