package com.ryuqq.core.api;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.ryuqq.core.api.exception.CoreException;
import com.ryuqq.core.domain.exception.DomainException;
import com.ryuqq.core.enums.ErrorType;
import com.ryuqq.core.events.SlackErrorAlertMessageEvent;
import com.ryuqq.core.logging.LogContext;
import com.ryuqq.core.logging.LogContextManager;
import com.ryuqq.core.logging.QueryLogger;

@Aspect
@Component
public class ApiLoggingAspect   {

	private final static String API_LAYER = "API";
	private final ApiEventPublisher apiEventPublisher;

	public ApiLoggingAspect(ApiEventPublisher apiEventPublisher) {
		this.apiEventPublisher = apiEventPublisher;
	}

	@Around("execution(* com.ryuqq.core.api.controller.v1..*(..)) ")
	public Object logApiLayer(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();

		LogContextManager.initialize(joinPoint, API_LAYER);
		Throwable caughtException = null; // 예외를 저장할 변수

		try {
			return joinPoint.proceed();
		} catch (CoreException | DomainException e) {
			LogContextManager.logToContext(joinPoint, e, startTime, API_LAYER);
			caughtException = e;
			throw e;
		} catch (Exception e) {
			CoreException wrappedException = new CoreException(
				"서버에 잠시 에러가 발생했습니다. 잠시 후 다시 시도해 주세요.",
				ErrorType.UNEXPECTED_ERROR,
				e
			);
			LogContextManager.logToContext(joinPoint, wrappedException, startTime, API_LAYER);
			caughtException = wrappedException;
			throw wrappedException;
		} finally {
			if (caughtException != null) {
				LogContext.getLogEntries()
					.stream()
					.filter(entry -> entry.getLogLevel().isLogRequired())
					.forEach(QueryLogger::log);

				String nestedLog = LogContextManager.finalizeAndLog();
				if (nestedLog != null && !nestedLog.isEmpty()) {
					apiEventPublisher.publish(new SlackErrorAlertMessageEvent(nestedLog));
				}
			}

			LogContextManager.clear();
		}

	}

}
