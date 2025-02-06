package com.ryuqq.core.domain;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.exception.DomainException;
import com.ryuqq.core.enums.ErrorType;

@Aspect
@Component
public class DomainExceptionWrapperAspect {

	@Around("execution(* com.ryuqq.core.domain..*(..))")
	public Object logDomainLayer(ProceedingJoinPoint joinPoint) throws Throwable {

		try {
			return joinPoint.proceed();
		} catch (DomainException e) {
			throw e;
		} catch (RuntimeException e) {
			ErrorType errorType = ErrorTypeExtractor.extractErrorType(e);
			if (errorType != null) {
				throw new DomainException(errorType, e);
			}
			throw new DomainException(ErrorType.UNEXPECTED_ERROR, e);
		} catch (Exception e) {
			throw new DomainException(ErrorType.UNEXPECTED_ERROR, e);
		}
	}

}
