package com.ryuqq.core.api;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ryuqq.core.api.exception.CoreException;
import com.ryuqq.core.domain.exception.DomainException;
import com.ryuqq.core.enums.ErrorType;
import com.ryuqq.core.logging.AopLogEntry;
import com.ryuqq.core.logging.LogEntryFactory;
import com.ryuqq.core.storage.db.exception.RdsStorageException;
import com.ryuqq.core.utils.AopUtils;
import com.ryuqq.core.utils.TraceIdHolder;

@Aspect
@Component
public class ApiLoggingAspect   {

	private final ApiErrorSlackNotificationService slackNotificationService;

	private static final Logger log = LoggerFactory.getLogger(ApiLoggingAspect.class);
	private static final String API_LAYER = "API";

	public ApiLoggingAspect(ApiErrorSlackNotificationService slackNotificationService) {
		this.slackNotificationService = slackNotificationService;
	}

	@Pointcut("within(@org.springframework.stereotype.Service *) && !within(com.ryuqq.core.api.ApiErrorSlackNotificationService)")
	public void apiLayerMethods() {}

	@Around("apiLayerMethods()")
	public Object logApiLayer(ProceedingJoinPoint joinPoint) throws Throwable {
		String traceId = TraceIdHolder.getTraceId();
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		Map<String, Object> args = AopUtils.extractArgs(joinPoint);
		long startTime = System.currentTimeMillis();

		try {
			return joinPoint.proceed();
		} catch (RdsStorageException e) {
			throw new CoreException("서버에 잠시 에러가 발생했습니다. 잠시 후 다시 시도해 주세요.", ErrorType.UNEXPECTED_ERROR, e);
		} catch (DomainException e) {
			throw e;
		} catch (Exception e) {
			CoreException wrappedException = new CoreException(
				"서버에 잠시 에러가 발생했습니다. 잠시 후 다시 시도해 주세요.",
				ErrorType.UNEXPECTED_ERROR,
				e
			);
			logError(createLogEntry(traceId, className, methodName, args, wrappedException, System.currentTimeMillis() - startTime));
			throw wrappedException;
		}
	}

	private AopLogEntry createLogEntry(String traceId, String className, String methodName, Map<String, Object> args, Throwable exception, long executionTime) {
		return LogEntryFactory.createAopLogEntry(
			traceId,
			API_LAYER,
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
