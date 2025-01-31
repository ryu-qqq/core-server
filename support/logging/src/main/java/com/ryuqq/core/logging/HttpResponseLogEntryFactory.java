package com.ryuqq.core.logging;


public class HttpResponseLogEntryFactory {

	private HttpResponseLogEntryFactory() {
	}

	public static HttpResponseLogEntry createHttpResponseLogEntry(int status, String body) {
		return new HttpResponseLogEntry(status, body);
	}

}
