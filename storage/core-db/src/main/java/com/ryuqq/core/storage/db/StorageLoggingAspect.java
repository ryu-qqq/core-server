package com.ryuqq.core.storage.db;

import com.ryuqq.core.enums.ErrorType;
import com.ryuqq.core.logging.AopLogEntry;
import com.ryuqq.core.logging.LogEntryFactory;
import com.ryuqq.core.logging.SqlLogEntry;
import com.ryuqq.core.storage.db.exception.RdsStorageException;
import com.ryuqq.core.storage.db.exception.SlowQueryException;
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
public class StorageLoggingAspect {

	private final RdsErrorSlackNotificationService slackNotificationService;

	private static final Logger log = LoggerFactory.getLogger(StorageLoggingAspect.class);
	private static final String STORAGE_LAYER = "STORAGE";

	public StorageLoggingAspect(RdsErrorSlackNotificationService slackNotificationService) {
		this.slackNotificationService = slackNotificationService;
	}

	@Pointcut("execution(* com.ryuqq.core.storage.db..*(..)) && @annotation(org.springframework.stereotype.Repository)")
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
			logError(createLogEntry(className, methodName, e));
			return null;
		} catch (RdsStorageException e) {
			logError(createLogEntry(traceId, className, methodName, args, e, System.currentTimeMillis() - startTime));
			throw e;
		} catch (Exception e) {
			RdsStorageException wrappedException = new RdsStorageException(
				String.format("Unexpected error in %s.%s", className, methodName), ErrorType.UNEXPECTED_ERROR, e
			);
			logError(createLogEntry(traceId, className, methodName, args, wrappedException, System.currentTimeMillis() - startTime));
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


	private void logError(AopLogEntry logEntry) {
		log.error(logEntry.toString(), logEntry.exception());
		slackNotificationService.sendErrorAlert(logEntry);
	}



}
