package com.ryuqq.core.logging;

import org.aspectj.lang.ProceedingJoinPoint;

import com.ryuqq.core.enums.LogLevel;
import com.ryuqq.core.utils.AopUtils;
import com.ryuqq.core.utils.TraceIdHolder;

public class AopLogEntryFactory {


	public static AopLogEntry createAopLogEntryWhenStart(ProceedingJoinPoint joinPoint, String layer){

		return new AopLogEntry(
			TraceIdHolder.getTraceId(),
			layer,
			joinPoint.getTarget().getClass().getSimpleName(),
			joinPoint.getSignature().getName(),
			AopUtils.extractArgs(joinPoint),
			null,
			0,
			LogLevel.INFO,
			extractCallerData(joinPoint)
		);
	}


	public static AopLogEntry createAopLogEntryWhenSuccess(ProceedingJoinPoint joinPoint, long startTime, String layer){
		long executionTime = System.currentTimeMillis() - startTime;

		return new AopLogEntry(
			TraceIdHolder.getTraceId(),
			layer,
			joinPoint.getTarget().getClass().getSimpleName(),
			joinPoint.getSignature().getName(),
			AopUtils.extractArgs(joinPoint),
			null,
			executionTime,
			LogLevel.INFO,
			extractCallerData(joinPoint)
		);
	}

	public static AopLogEntry createAopLogEntryWhenFailed(ProceedingJoinPoint joinPoint, Throwable exception, long startTime, String layer){
		long executionTime = System.currentTimeMillis() - startTime;

		return new AopLogEntry(
			TraceIdHolder.getTraceId(),
			layer,
			joinPoint.getTarget().getClass().getSimpleName(),
			joinPoint.getSignature().getName(),
			AopUtils.extractArgs(joinPoint),
			exception,
			executionTime,
			LogLevel.ERROR,
			extractCallerData(joinPoint)
		);
	}

	private static String extractCallerData(ProceedingJoinPoint joinPoint) {
		return joinPoint.getSignature().toShortString();
	}
}
