package com.ryuqq.core.domain;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.ryuqq.core.logging.AbstractLayerLoggingAspect;
import com.ryuqq.core.logging.LogEntry;
import com.ryuqq.core.logging.LogEntryFactory;

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
		LogEntry logEntry = LogEntryFactory.createLogEntry(
			traceId,
			DOMAIN_LAYER,
			className,
			methodName,
			args,
			null,
			0L
		);

		return logEntry.toJson();
	}

	@Override
	protected String createPostLogMessage(String traceId, String className, String methodName, Object[] args,
										  Object result, long executionTime) {

		LogEntry logEntry = LogEntryFactory.createLogEntry(
			traceId,
			DOMAIN_LAYER,
			className,
			methodName,
			args,
			result,
			executionTime
		);

		return logEntry.toJson();
	}

	@Override
	protected String createErrorLogMessage(String traceId, String className, String methodName, Object[] args,
										   Exception ex, long executionTime) {
		return String.format(
			"[%s][TraceId: %s] Error in %s.%s after %d ms. Exception: %s",
			DOMAIN_LAYER, traceId, className, methodName, executionTime, ex.getMessage()
		);
	}

}
