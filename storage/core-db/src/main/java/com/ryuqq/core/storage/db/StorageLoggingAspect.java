package com.ryuqq.core.storage.db;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.ryuqq.core.enums.ErrorType;
import com.ryuqq.core.logging.AopLogEntry;
import com.ryuqq.core.logging.LogContext;
import com.ryuqq.core.logging.LogEntryFactory;
import com.ryuqq.core.logging.SqlLogEntry;
import com.ryuqq.core.storage.db.exception.RdsStorageException;
import com.ryuqq.core.storage.db.exception.SlowQueryException;
import com.ryuqq.core.utils.AopUtils;
import com.ryuqq.core.utils.TraceIdHolder;

@Aspect
@Component
public class StorageLoggingAspect {

	private static final String STORAGE_LAYER = "STORAGE";

	@Pointcut("execution(* com.ryuqq.core.storage.db..*(..))")
	public void repositoryMethods() {}

	@Around("repositoryMethods()")
	public Object handleStorageLayerExceptions(ProceedingJoinPoint joinPoint) throws Throwable {
		String traceId = TraceIdHolder.getTraceId();
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		Map<String, Object> args = AopUtils.extractArgs(joinPoint);
		long startTime = System.currentTimeMillis();

		try {
			return joinPoint.proceed();
		} catch (SlowQueryException e) {
			AopLogEntry slowQueryLogEntry = createLogEntry(className, methodName, e);
			LogContext.addNestedLogEntry(slowQueryLogEntry);
			return null;
		} catch (RdsStorageException e) {
			AopLogEntry aopLogEntry = createLogEntry(traceId, className, methodName, args, e, System.currentTimeMillis() - startTime);
			LogContext.addNestedLogEntry(aopLogEntry);
			throw e;
		} catch (Exception e) {
			RdsStorageException wrappedException = new RdsStorageException(ErrorType.UNEXPECTED_ERROR,
				String.format("Unexpected error in %s.%s", className, methodName), e
			);
			AopLogEntry unexpectedErrorLogEntry = createLogEntry(traceId, className, methodName, args, wrappedException, System.currentTimeMillis() - startTime);
			LogContext.addNestedLogEntry(unexpectedErrorLogEntry);

			throw wrappedException;
		}
	}

	private AopLogEntry createLogEntry(String traceId, String className, String methodName, Map<String, Object> args, Throwable exception, long executionTime) {
		return LogEntryFactory.createAopLogEntry(
			traceId,
			STORAGE_LAYER,
			className,
			methodName,
			args,
			exception,
			executionTime
		);
	}

	private AopLogEntry createLogEntry(String className, String methodName, SlowQueryException e) {
		SqlLogEntry logEntry = e.getSqlLogEntry();

		return LogEntryFactory.createAopLogEntry(
			logEntry.traceId(),
			STORAGE_LAYER,
			className,
			methodName,
			logEntry.args(),
			e,
			logEntry.executionTime()
		);
	}



}
