package com.ryuqq.core.storage.db;

import com.ryuqq.core.logging.LogEntryFactory;
import com.ryuqq.core.logging.SqlLogEntry;
import com.ryuqq.core.utils.TraceIdHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.ttddyy.dsproxy.ExecutionInfo;
import net.ttddyy.dsproxy.QueryInfo;
import net.ttddyy.dsproxy.listener.QueryExecutionListener;
import net.ttddyy.dsproxy.proxy.ParameterSetOperation;

public class SlowQueryListener implements QueryExecutionListener {

	private static final Logger log = LoggerFactory.getLogger(SlowQueryListener.class);
	protected static final String STORAGE_LAYER = "STORAGE";

	private final long thresholdMs;

	public SlowQueryListener(long thresholdMs) {
		this.thresholdMs = thresholdMs;
	}

	@Override
	public void beforeQuery(ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
		// 쿼리 실행 전에 동작 추가 가능
	}

	@Override
	public void afterQuery(ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
		long executionTime = execInfo.getElapsedTime();
		String traceId = TraceIdHolder.getTraceId();

		for (QueryInfo queryInfo : queryInfoList) {
			String sql = queryInfo.getQuery();
			Map<Object, Object> params = extractParams(queryInfo);

			SqlLogEntry logEntry = executionTime > thresholdMs
				? LogEntryFactory.createSqlLogEntryWithError(traceId, "STORAGE", sql, params, executionTime, "Slow query detected")
				: LogEntryFactory.createSqlLogEntry(traceId, "STORAGE", sql, params, executionTime);

			if (executionTime > thresholdMs) {
				log.warn(logEntry.toJson());
			} else {
				log.info(logEntry.toJson());
			}
		}
	}

	private Map<Object, Object> extractParams(QueryInfo queryInfo) {
		Map<Object, Object> params = new HashMap<>();
		if (queryInfo.getParametersList() != null) {
			List<List<ParameterSetOperation>> parameterValues = queryInfo.getParametersList();
			for (List<ParameterSetOperation> operations : parameterValues) {
				for (ParameterSetOperation operation : operations) {
					params.put(operation.getArgs()[0], operation.getArgs()[1]);
				}
			}
		}
		return params;
	}


}
