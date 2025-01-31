package com.ryuqq.core.logging;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
	private final String stackTrace;
	private final String callerData;
	private final String hostName;
	private final String processId;
	private final String applicationName;

	AopLogEntry(String traceId, String layer, String className, String methodName, Map<String, Object> args,
				Throwable exception, long executionTime, LogLevel logLevel, String callerData) {
		super(traceId, layer);
		this.className = className;
		this.methodName = methodName;
		this.args = args;
		this.exception = exception;
		this.executionTime = executionTime;
		this.logLevel = logLevel;
		this.stackTrace = (exception != null) ? extractStackTrace(exception) : null;
		this.callerData = callerData;
		this.hostName = getHostName();
		this.processId = getProcessId();
		this.applicationName = System.getProperty("spring.application.name", "UNKNOWN_APP");
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

	private String extractStackTrace(Throwable e) {
		int MAX_STACKTRACE_LINES = 5;  // 표시할 최대 줄 수
		return Arrays.stream(e.getStackTrace())
			.limit(MAX_STACKTRACE_LINES)  // ⚡️ 최대 5줄까지만 저장
			.map(StackTraceElement::toString)
			.collect(Collectors.joining("\n")) + "\n... 생략됨";
	}


	public String getHostName() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			return "UNKNOWN_HOST";
		}
	}

	public String getProcessId() {
		return ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
	}

	@Override
	public LogLevel getLogLevel() {
		return logLevel;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public String getCallerData() {
		return callerData;
	}

	public String getApplicationName() {
		return applicationName;
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
			&& Objects.equals(exception, that.exception)
			&& logLevel
			== that.logLevel
			&& Objects.equals(stackTrace, that.stackTrace)
			&& Objects.equals(callerData, that.callerData)
			&& Objects.equals(hostName, that.hostName)
			&& Objects.equals(processId, that.processId)
			&& Objects.equals(applicationName, that.applicationName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(className, methodName, args, exception, executionTime, logLevel, stackTrace, callerData,
			hostName, processId, applicationName);
	}

	@Override
	public String toString() {
		return "AopLogEntry{" +
			"className='" + className + '\'' +
			", methodName='" + methodName + '\'' +
			", executionTime=" + executionTime +
			", logLevel=" + logLevel +
			", stackTrace='" + stackTrace + '\'' +
			", callerData='" + callerData + '\'' +
			", hostName='" + hostName + '\'' +
			", processId='" + processId + '\'' +
			", applicationName='" + applicationName + '\'' +
			'}';
	}


}
