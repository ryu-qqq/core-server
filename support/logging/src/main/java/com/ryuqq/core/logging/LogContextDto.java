package com.ryuqq.core.logging;

import java.util.List;

public class LogContextDto {
	private final String traceId;
	private final List<AopLogEntryDto> entries;

	public LogContextDto(String traceId, List<AopLogEntryDto> entries) {
		this.traceId = traceId;
		this.entries = entries;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{\n");
		sb.append("  \"traceId\": \"").append(traceId).append("\",\n");
		sb.append("  \"entries\": [\n");
		for (int i = 0; i < entries.size(); i++) {
			sb.append("    ").append(entries.get(i).toString());
			if (i < entries.size() - 1) {
				sb.append(",");
			}
			sb.append("\n");
		}
		sb.append("  ]\n");
		sb.append("}");
		return sb.toString();
	}
}
