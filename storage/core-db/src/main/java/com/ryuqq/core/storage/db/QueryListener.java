package com.ryuqq.core.storage.db;

import java.util.List;

import net.ttddyy.dsproxy.ExecutionInfo;
import net.ttddyy.dsproxy.QueryInfo;
import net.ttddyy.dsproxy.listener.QueryExecutionListener;

import com.ryuqq.core.enums.LogLevel;
import com.ryuqq.core.logging.QueryLogger;
import com.ryuqq.core.logging.SqlLogEntry;
import com.ryuqq.core.logging.SqlLogEntryFactory;
import com.ryuqq.core.utils.TraceIdHolder;

public class QueryListener implements QueryExecutionListener {

	private final long slowQueryThresholdMs;
	private final long criticalQueryThresholdMs;
	private final QueryMetricsCollector metricsCollector;

	public QueryListener(long slowQueryThresholdMs, long criticalQueryThresholdMs,
						 QueryMetricsCollector metricsCollector) {
		this.slowQueryThresholdMs = slowQueryThresholdMs;
		this.criticalQueryThresholdMs = criticalQueryThresholdMs;
		this.metricsCollector = metricsCollector;
	}

	@Override
	public void beforeQuery(ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
		String traceId = TraceIdHolder.getTraceId();
		for (QueryInfo queryInfo : queryInfoList) {
			SqlLogEntry logEntry = SqlLogEntryFactory.createLogEntry(execInfo, queryInfo, traceId, 0, null, LogLevel.INFO, "BEFORE");
			QueryLogger.log(logEntry);
		}
	}

	@Override
	public void afterQuery(ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
		long executionTime = execInfo.getElapsedTime();
		String traceId = TraceIdHolder.getTraceId();

		for (QueryInfo queryInfo : queryInfoList) {
			LogLevel logLevel = QueryPerformanceEvaluator.evaluate(executionTime, slowQueryThresholdMs, criticalQueryThresholdMs);

			String errorMessage = switch (logLevel) {
				case ERROR -> "⚠️ Critical slow query detected!";
				case WARN -> "⚠️ Slow query detected!";
				default -> null;
			};

			metricsCollector.collect(execInfo.getDataSourceName(), queryInfoList, executionTime);

			SqlLogEntry logEntry = SqlLogEntryFactory.createLogEntry(execInfo, queryInfo, traceId, executionTime, errorMessage, logLevel, "AFTER");
			QueryLogger.log(logEntry);

		}
	}


}
