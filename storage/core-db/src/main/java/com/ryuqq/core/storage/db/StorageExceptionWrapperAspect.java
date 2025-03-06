package com.ryuqq.core.storage.db;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.stereotype.Component;

import com.ryuqq.core.enums.ErrorType;
import com.ryuqq.core.storage.db.exception.RdsStorageException;


@Aspect
@Component
public class StorageExceptionWrapperAspect {

	@Around("execution(* com.ryuqq.core.storage.db..*(..))")
	public Object handleStorageLayerExceptions(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			return joinPoint.proceed();
		} catch (TransientDataAccessException e) {
			throw new RdsStorageException(ErrorType.DATABASE_TRANSIENT_ERROR, e);
		} catch (NonTransientDataAccessException e) {
			throw new RdsStorageException(ErrorType.DATABASE_PERMANENT_ERROR, e);
		} catch (RdsStorageException e) {
			throw e;
		} catch (Exception e) {
			throw new RdsStorageException(ErrorType.UNEXPECTED_ERROR, e);
		}
	}


}
