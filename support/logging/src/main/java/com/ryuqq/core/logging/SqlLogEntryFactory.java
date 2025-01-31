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

		int affectedRows = extractAffectedRows(execInfo);
		long threadId = Thread.currentThread().threadId();
		String dbUser = extractDbUser(execInfo);
		String transactionStatus = extractTransactionStatus(execInfo);

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
			queryPhase,
			affectedRows,
			threadId,
			dbUser,
			transactionStatus
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

	private static int extractAffectedRows(ExecutionInfo execInfo) {
		if (execInfo.getResult() instanceof Integer result) {
			return result;
		}
		return -1;
	}

	private static String extractTransactionStatus(ExecutionInfo execInfo) {
		try {
			if (execInfo.getMethodArgs() != null) {
				for (Object arg : execInfo.getMethodArgs()) {
					if (arg instanceof java.sql.Connection connection) {
						boolean autoCommit = connection.getAutoCommit();
						return autoCommit ? "AUTO_COMMIT" : "TRANSACTION_ACTIVE";
					}
				}
			}
		} catch (Exception ignored) {}
		return "UNKNOWN";
	}


	private static String extractDbUser(ExecutionInfo execInfo) {
		try {
			if (execInfo.getMethodArgs() != null) {
				for (Object arg : execInfo.getMethodArgs()) {
					if (arg instanceof java.sql.Connection connection) {
						return connection.getMetaData().getUserName(); // DB 사용자명 가져오기
					}
				}
			}
		} catch (Exception ignored) {}
		return "UNKNOWN_USER"; // 가져올 수 없으면 기본값 반환
	}


}
