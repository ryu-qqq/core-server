package com.ryuqq.core.storage.db;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.ryuqq.core.logging.AbstractLayerLoggingAspect;
import com.ryuqq.core.logging.SimpleLogEntry;
import com.ryuqq.core.logging.LogEntryFactory;

@Aspect
@Component
public class StorageLoggingAspect extends AbstractLayerLoggingAspect  {

	protected static final String STORAGE_LAYER = "STORAGE";


	@Pointcut("execution(* com.ryuqq.core.storage.db..*(..)) "
		+ "&& !within(com.ryuqq.core.storage.db.config..*)")
	public void storageLayerMethods() {
	}

	@Around("storageLayerMethods()")
	public Object logStorageLayer(ProceedingJoinPoint joinPoint) throws Throwable {
		return logExecution(joinPoint);
	}

	@Override
	protected String createPreLogMessage(String traceId, String className, String methodName, Object[] args) {
		SimpleLogEntry simpleLogEntry = LogEntryFactory.createLogEntry(
			traceId,
			STORAGE_LAYER,
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
			STORAGE_LAYER,
			className,
			methodName,
			args,
			safeResult,
			executionTime
		);

		return simpleLogEntry.toJson();
	}

	@Override
	protected String createErrorLogMessage(String traceId, String className, String methodName, Object[] args,
										   Exception ex, long executionTime) {
		return String.format(
			"[%s][TraceId: %s] Error in %s.%s after %d ms. Exception: %s",
			STORAGE_LAYER, traceId, className, methodName, executionTime, ex.getMessage()
		);
	}

}
