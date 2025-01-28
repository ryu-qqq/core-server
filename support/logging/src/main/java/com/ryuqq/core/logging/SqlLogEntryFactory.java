package com.ryuqq.core.logging;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.ttddyy.dsproxy.ExecutionInfo;
import net.ttddyy.dsproxy.QueryInfo;
import net.ttddyy.dsproxy.proxy.ParameterSetOperation;

import com.ryuqq.core.enums.LogLevel;

public class SqlLogEntryFactory {

	public static SqlLogEntry createLogEntry(ExecutionInfo execInfo, QueryInfo queryInfo, String traceId, long executionTime, String errorMessage, LogLevel logLevel, String queryPhase) {
		return new SqlLogEntry(
			traceId,
			"STORAGE",
			queryInfo.getQuery(),
			extractParams(queryInfo),
			executionTime,
			errorMessage,
			logLevel,
			execInfo.getDataSourceName(),
			execInfo.getConnectionId(),
			execInfo.getIsolationLevel(),
			queryPhase
		);
	}

	public static Map<String, Object> extractParams(QueryInfo queryInfo) {
		Map<String, Object> params = new HashMap<>();
		if (queryInfo.getParametersList() != null) {
			for (List<ParameterSetOperation> operations : queryInfo.getParametersList()) {
				for (ParameterSetOperation operation : operations) {
					Object[] args = operation.getArgs();
					if (args.length > 1) {
						params.put(args[0].toString(), args[1]);
					}
				}
			}
		}
		return params;
	}

}
