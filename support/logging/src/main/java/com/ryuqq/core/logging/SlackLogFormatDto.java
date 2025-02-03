package com.ryuqq.core.logging;

import java.util.Map;
import java.util.Objects;

public class SlackLogFormatDto {
	private final String className;
	private final String methodName;
	private final Map<String, Object> args;
	private final long executionTime;
	private final String exceptionMessage;
	private final String filteredStackTrace;
	private final String callerData;
	private final String hostName;
	private final String processId;
	private final String applicationName;

	public SlackLogFormatDto(String className, String methodName, Map<String, Object> args, long executionTime, String exceptionMessage,
						  String stackTrace, String callerData, String hostName, String processId,
						  String applicationName) {
		this.className = className;
		this.methodName = methodName;
		this.args = args;
		this.executionTime = executionTime;
		this.exceptionMessage = exceptionMessage;
		this.filteredStackTrace = filterStackTrace(stackTrace);
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


	public String getCallerData() {
		return callerData;
	}

	public String getFilteredStackTrace() {
		return filteredStackTrace;
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


	private String filterStackTrace(String stackTrace) {
		if (stackTrace == null || stackTrace.isEmpty()) return "No stack trace available.";

		String[] lines = stackTrace.split("\n");
		StringBuilder filtered = new StringBuilder();
		int count = 0;

		for (String line : lines) {
			if (line.contains("com.ryuqq.core") || line.contains("java.") || line.contains("org.springframework.")) {
				filtered.append(line.trim()).append("\n");
				if (++count >= 10) break;  // 상위 5개만 표시
			}
		}

		if (filtered.isEmpty()) {
			return "Filtered stack trace is empty.";
		}
		return filtered.toString();
	}

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		SlackLogFormatDto that = (SlackLogFormatDto) object;
		return executionTime
			== that.executionTime
			&& Objects.equals(className, that.className)
			&& Objects.equals(methodName, that.methodName)
			&& Objects.equals(args, that.args)
			&& Objects.equals(exceptionMessage, that.exceptionMessage)
			&& Objects.equals(filteredStackTrace, that.filteredStackTrace)
			&& Objects.equals(callerData, that.callerData)
			&& Objects.equals(hostName, that.hostName)
			&& Objects.equals(processId, that.processId)
			&& Objects.equals(applicationName, that.applicationName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(className, methodName, args, executionTime, exceptionMessage, filteredStackTrace,
			callerData,
			hostName, processId, applicationName);
	}

	@Override
	public String toString() {
		return "SlackLogFormatDto{"
			+
			"className='"
			+ className
			+ '\''
			+
			", methodName='"
			+ methodName
			+ '\''
			+
			", args="
			+ args
			+
			", executionTime="
			+ executionTime
			+
			", exceptionMessage='"
			+ exceptionMessage
			+ '\''
			+
			", filteredStackTrace='"
			+ filteredStackTrace
			+ '\''
			+
			", callerData='"
			+ callerData
			+ '\''
			+
			", hostName='"
			+ hostName
			+ '\''
			+
			", processId='"
			+ processId
			+ '\''
			+
			", applicationName='"
			+ applicationName
			+ '\''
			+
			'}';
	}
}
