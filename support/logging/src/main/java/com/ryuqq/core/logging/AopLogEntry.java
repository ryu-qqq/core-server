package com.ryuqq.core.logging;

import java.util.Map;
import java.util.Objects;

import com.ryuqq.core.enums.LogLevel;

/**
 * AOP 로그 엔트리 구현체
 */
public final class AopLogEntry extends AbstractLogEntry {
	private final String className;
	private final String methodName;
	private final Map<String, Object> args;
	private final Throwable exception;
	private final long executionTime;
	private final LogLevel logLevel;

	AopLogEntry(String traceId, String layer, String className, String methodName, Map<String, Object> args,
				Throwable exception, long executionTime, LogLevel logLevel) {
		super(traceId, layer);
		this.className = className;
		this.methodName = methodName;
		this.args = args;
		this.exception = exception;
		this.executionTime = executionTime;
		this.logLevel = logLevel;
	}

	public String getClassName() {
		return className;
	}

	public String getMethodName() {
		return methodName;
	}

	public Map<String, Object> getArgs() {
		return args;
	}

	public Throwable getException() {
		return exception;
	}

	public long getExecutionTime() {
		return executionTime;
	}

	@Override
	public LogLevel getLogLevel() {
		return logLevel;
	}

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		AopLogEntry that = (AopLogEntry) object;
		return executionTime
			== that.executionTime
			&& Objects.equals(className, that.className)
			&& Objects.equals(methodName, that.methodName)
			&& Objects.equals(args, that.args)
			&& Objects.equals(exception, that.exception);
	}

	@Override
	public int hashCode() {
		return Objects.hash(className, methodName, args, exception, executionTime);
	}

	@Override
	public String toString() {
		return super.toString() +
			"- Class: " + className + "\n" +
			"- Method: " + methodName + "\n" +
			"- Arguments: " + truncate(args.toString()) + "\n" +
			"- Execution Time: " + executionTime + "ms\n" +
			"- Exception: " + formatException(exception) + "\n";
	}


}
