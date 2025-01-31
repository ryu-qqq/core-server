package com.ryuqq.core.logging;


public class HttpResponseLogEntryFactory {

	private HttpResponseLogEntryFactory() {
	}

	public static HttpResponseLogEntry createHttpResponseLogEntry(int status, String body, long executionTime) {
		return new HttpResponseLogEntry(status, body, executionTime);
	}

}
