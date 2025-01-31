package com.ryuqq.core.logging;

import java.util.Objects;

import com.ryuqq.core.enums.LogLevel;
import com.ryuqq.core.utils.TraceIdHolder;

public class HttpResponseLogEntry implements LogEntry{

	private final String traceId;
	private final String layer;
	private final LogLevel logLevel;
	private final int status;
	private final String body;

	protected HttpResponseLogEntry(int status, String body) {
		this.traceId = TraceIdHolder.getTraceId();
		this.layer = "HTTP";
		this.logLevel = status == 200 ? LogLevel.INFO : LogLevel.ERROR;
		this.status = status;
		this.body = body;
	}

	@Override
	public String getTraceId() {
		return traceId;
	}

	@Override
	public String getLayer() {
		return layer;
	}

	@Override
	public LogLevel getLogLevel() {
		return null;
	}

	public int getStatus() {
		return status;
	}

	public String getBody() {
		return body;
	}

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		HttpResponseLogEntry that = (HttpResponseLogEntry) object;
		return status
			== that.status
			&& Objects.equals(traceId, that.traceId)
			&& Objects.equals(layer, that.layer)
			&& logLevel
			== that.logLevel
			&& Objects.equals(body, that.body);
	}

	@Override
	public int hashCode() {
		return Objects.hash(traceId, layer, logLevel, status, body);
	}

	@Override
	public String toString() {
		return String.format(
			"""
			[HTTP RESPONSE]
			 TraceId: %s
			- STATUS: %s
			- Body: %s
			""",
			traceId, status, body
		);
	}

}
