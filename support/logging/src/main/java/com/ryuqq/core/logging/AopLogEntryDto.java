package com.ryuqq.core.logging;

import java.util.Map;
import java.util.Objects;

public class AopLogEntryDto {
	private final String className;
	private final String methodName;
	private final Map<String, Object> args;
	private final long executionTime;
	private final String exceptionMessage;
	private final String stackTrace;
	private final String callerData;
	private final String hostName;
	private final String processId;
	private final String applicationName;

	public AopLogEntryDto(String className, String methodName, Map<String, Object> args, long executionTime, String exceptionMessage,
						  String stackTrace, String callerData, String hostName, String processId,
						  String applicationName) {
		this.className = className;
		this.methodName = methodName;
		this.args = args;
		this.executionTime = executionTime;
		this.exceptionMessage = exceptionMessage;
		this.stackTrace = stackTrace;
		this.callerData = callerData;
		this.hostName = hostName;
		this.processId = processId;
		this.applicationName = applicationName;
	}

	public String getClassName() {
		return className;
	}

	public long getExecutionTime() {
		return executionTime;
	}

	public String getMethodName() {
		return methodName;
	}

	public Map<String, Object> getArgs() {
		return args;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public String getCallerData() {
		return callerData;
	}

	public String getHostName() {
		return hostName;
	}

	public String getProcessId() {
		return processId;
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
		AopLogEntryDto that = (AopLogEntryDto) object;
		return executionTime
			== that.executionTime
			&& Objects.equals(className, that.className)
			&& Objects.equals(methodName, that.methodName)
			&& Objects.equals(args, that.args)
			&& Objects.equals(exceptionMessage, that.exceptionMessage)
			&& Objects.equals(stackTrace, that.stackTrace)
			&& Objects.equals(callerData, that.callerData)
			&& Objects.equals(hostName, that.hostName)
			&& Objects.equals(processId, that.processId)
			&& Objects.equals(applicationName, that.applicationName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(className, methodName, args, executionTime, exceptionMessage, stackTrace, callerData,
			hostName,
			processId, applicationName);
	}

	@Override
	public String toString() {
		return String.format(
			"""
				{
				  "className": "%s",
				  "methodName": "%s",
				  "args": %s,
				  "executionTime": %d ms,
				  "exceptionMessage": "%s",
				  "stackTrace": "%s",
				  "callerData": "%s",
				  "hostName": "%s",
				  "processId": "%s",
				  "applicationName": "%s"
				}""",
			className,
			methodName,
			args != null ? args.toString() : "null",
			executionTime,
			exceptionMessage != null ? exceptionMessage.replaceAll("\n", " ") : "No exception message",
			stackTrace != null ? stackTrace.replaceAll("\n", " ") : "No stack trace",
			callerData,
			hostName,
			processId,
			applicationName
		);
	}

}
