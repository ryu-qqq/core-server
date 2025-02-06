package com.ryuqq.core.api;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.ryuqq.core.api.exception.CoreException;
import com.ryuqq.core.domain.ErrorTypeExtractor;
import com.ryuqq.core.enums.ErrorType;

@Aspect
@Component
public class ApiExceptionWrapperAspect {

	@Around("execution(* com.ryuqq.core.api.controller.v1..*(..)) ")
	public Object logApiLayer(ProceedingJoinPoint joinPoint) throws Throwable {

		try {
			return joinPoint.proceed();
		} catch (CoreException e) {
			throw e;
		} catch (RuntimeException e) {
			ErrorType errorType = ErrorTypeExtractor.extractErrorType(e);
			if (errorType != null) {
				throw new CoreException(errorType, e);
			}
			throw new CoreException(ErrorType.UNEXPECTED_ERROR, e);
		} catch (Exception e) {
			throw new CoreException(ErrorType.UNEXPECTED_ERROR, e);
		}
	}

}
