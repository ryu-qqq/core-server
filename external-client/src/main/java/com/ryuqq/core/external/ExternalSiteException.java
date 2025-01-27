package com.ryuqq.core.external;

public class ExternalSiteException extends RuntimeException {

	public ExternalSiteException(String message) {
		super(message);
	}

	public ExternalSiteException(String message, Throwable cause) {
		super(message, cause);
	}
}
