package com.ryuqq.core.api;

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
public class ApiLoggingAspect extends AbstractLayerLoggingAspect  {


	private static final String API_LAYER = "API";

	@Pointcut("execution(* com.ryuqq.core.api.controller.v1..*(..)) ")
	public void apiLayerMethods() {
	}

	@Around("apiLayerMethods()")
	public Object logApiLayer(ProceedingJoinPoint joinPoint) throws Throwable {
		return logExecution(joinPoint);
	}

	@Override
	protected String createPreLogMessage(String traceId, String className, String methodName, Object[] args) {

		LogEntry logEntry = LogEntryFactory.createLogEntry(
			traceId,
			API_LAYER,
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
			API_LAYER,
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
			API_LAYER, traceId, className, methodName, executionTime, ex.getMessage()
		);
	}


}
