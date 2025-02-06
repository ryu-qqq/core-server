package com.ryuqq.core.logging;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.monikit.core.ExceptionLog;

public class ExceptionLogFormatter {

	public static String formatExceptionLog(ExceptionLog logEntry) {
		return """
            :pushpin: *TRACE ID*: `%s`
            :rocket: *ERROR CATEGORY*: `%s`
            :stopwatch: *ERROR SOURCE CLASS*: `%s`
            :checkered_flag: *ERROR SOURCE METHOD*: `%s`
            :warning: *EXCEPTION*: `%s`
            :page_with_curl: *Filtered Stack Trace*:
            ```java
            %s
            ```
            :computer: *Host*: `%s`
            :gear: *Process ID*: `%s`
            :factory: *Application*: `%s`
            """
			.formatted(
				logEntry.getTraceId(),
				logEntry.getErrorCategory(),
				logEntry.getSourceClass(),
				logEntry.getSourceMethod(),
				logEntry.getExceptionMessage(),
				getShortStackTrace(logEntry),
				getHostName(),
				getProcessId(),
				"Core Server"
			);
	}

	private static String getShortStackTrace(ExceptionLog logEntry) {
		String[] lines = logEntry.getStackTrace().split("\n");
		StringBuilder sb = new StringBuilder();
		int maxLines = 5; // 최대 5줄까지만 출력

		for (int i = 0; i < Math.min(lines.length, maxLines); i++) {
			sb.append(lines[i]).append("\n");
		}

		if (lines.length > maxLines) {
			sb.append("... 이하 생략 ...");
		}

		return sb.toString();
	}

	private static String getHostName() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			return "Unknown";
		}
	}

	private static String getProcessId() {
		String jvmName = ManagementFactory.getRuntimeMXBean().getName();
		return jvmName.contains("@") ? jvmName.split("@")[0] : "Unknown";
	}
}
