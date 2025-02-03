package com.ryuqq.core.enums;

public enum LogLevel {
    ERROR, WARN, INFO, DEBUG, TRACE;

	public boolean isLogRequired(){
		return this.equals(ERROR) || this.equals(WARN);
	}

}
