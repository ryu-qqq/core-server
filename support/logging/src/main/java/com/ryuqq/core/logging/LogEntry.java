package com.ryuqq.core.logging;

import com.ryuqq.core.enums.LogLevel;

public interface LogEntry {

	String getTraceId();
	String getLayer();
	String toString();
	LogLevel getLogLevel();

}
