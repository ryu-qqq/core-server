package com.ryuqq.core.storage.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LogLevel;

import net.ttddyy.dsproxy.ExecutionInfo;
import net.ttddyy.dsproxy.QueryInfo;
import net.ttddyy.dsproxy.listener.QueryExecutionListener;
import net.ttddyy.dsproxy.proxy.ParameterSetOperation;

import com.ryuqq.core.enums.ErrorType;
import com.ryuqq.core.logging.LogEntryFactory;
import com.ryuqq.core.logging.SqlLogEntry;
import com.ryuqq.core.storage.db.exception.SlowQueryException;
import com.ryuqq.core.utils.TraceIdHolder;

public class QueryListener implements QueryExecutionListener {

	private static final Logger log = LoggerFactory.getLogger(QueryListener.class);
	private static final String STORAGE_LAYER = "STORAGE";

	private final long slowQueryThresholdMs;
	private final long criticalQueryThresholdMs;


	public QueryListener(long slowQueryThresholdMs, long criticalQueryThresholdMs) {
		this.slowQueryThresholdMs = slowQueryThresholdMs;
		this.criticalQueryThresholdMs = criticalQueryThresholdMs;
	}

	@Override
	public void beforeQuery(ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
		String traceId = TraceIdHolder.getTraceId();

		for (QueryInfo queryInfo : queryInfoList) {
			SqlLogEntry logEntry = createLogEntry(execInfo, queryInfo, traceId, 0, null, LogLevel.INFO, "BEFORE");
			log(LogLevel.INFO, logEntry);
		}
	}

	@Override
	public void afterQuery(ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
		long executionTime = execInfo.getElapsedTime();
		String traceId = TraceIdHolder.getTraceId();

		for (QueryInfo queryInfo : queryInfoList) {
			LogLevel logLevel = determineLogLevel(executionTime);
			String errorMessage = logLevel == LogLevel.ERROR ? "Critical slow query detected"
				: logLevel == LogLevel.WARN ? "Slow query detected"
				: null;

			SqlLogEntry logEntry = createLogEntry(execInfo, queryInfo, traceId, executionTime, errorMessage, logLevel, "AFTER");

			if (logLevel == LogLevel.ERROR) {
				notifyCriticalAlert(logEntry);
			}

			log(logLevel, logEntry);
		}
	}

	private void log(LogLevel logLevel, SqlLogEntry logEntry) {
		switch (logLevel) {
			case ERROR -> log.error(logEntry.toString());
			case WARN -> log.warn(logEntry.toString());
			default -> log.info(logEntry.toString());
		}
	}


	private LogLevel determineLogLevel(long executionTime) {
		if (executionTime > criticalQueryThresholdMs) {
			return LogLevel.ERROR;
		} else if (executionTime > slowQueryThresholdMs) {
			return LogLevel.WARN;
		} else {
			return LogLevel.INFO;
		}
	}

	private SqlLogEntry createLogEntry(ExecutionInfo execInfo, QueryInfo queryInfo, String traceId, long executionTime, String errorMessage, LogLevel logLevel, String queryPhase) {
		return LogEntryFactory.createSqlLogEntry(
			traceId,
			STORAGE_LAYER,
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

	private Map<String, Object> extractParams(QueryInfo queryInfo) {
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

	private void notifyCriticalAlert(SqlLogEntry logEntry) {
		log.error("[Critical Alert] {}", logEntry.toString());
		throw new SlowQueryException(ErrorType.UNEXPECTED_ERROR, logEntry);

	}

}
