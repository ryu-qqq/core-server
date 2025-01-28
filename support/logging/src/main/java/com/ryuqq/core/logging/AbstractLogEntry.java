package com.ryuqq.core.logging;

import java.io.PrintWriter;
import java.io.StringWriter;

public abstract class AbstractLogEntry implements LogEntry {
	private final String traceId;
	private final String layer;

	protected AbstractLogEntry(String traceId, String layer) {
		this.traceId = traceId;
		this.layer = layer;
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
	public String toString() {
		return "- Trace ID: " + traceId + "\n" + "- Layer: " + layer + "\n";
	}

	protected String truncate(String data) {
		return data.length() > 500 ? data.substring(0, 500) + "..." : data;
	}

	protected String formatException(Throwable exception) {
		if (exception == null) return "None";
		StringWriter sw = new StringWriter();
		exception.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}
}
