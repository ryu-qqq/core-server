package com.ryuqq.core.external;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.ryuqq.core.enums.ErrorType;

@Aspect
@Component
public class ExternalClientExceptionWrapperAspect {

	@Around("execution(* com.ryuqq.core.external..*(..))")
	public Object wrapExternalExceptions(ProceedingJoinPoint joinPoint) throws Throwable {

		try {
			return joinPoint.proceed();
		} catch (ExternalSiteException e) {
			throw e;
		} catch (Exception e) {
			throw new ExternalSiteException(ErrorType.UNEXPECTED_ERROR, e);
		}
	}

}
