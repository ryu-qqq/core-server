package com.ryuqq.core.storage.db;

import java.util.List;

import org.springframework.stereotype.Component;

import net.ttddyy.dsproxy.QueryInfo;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

@Component
public class QueryMetricsCollector {

	private final MeterRegistry meterRegistry;

	public QueryMetricsCollector(MeterRegistry meterRegistry) {
		this.meterRegistry = meterRegistry;
	}

	public void collect(String dataSource, List<QueryInfo> queryInfoList, long executionTime) {
		String queryGroup = QueryGrouper.groupQueries(queryInfoList);
		String queryType = determineQueryType(queryInfoList.getFirst().getQuery());

		Timer.builder("db.query.execution.time")
			.tag("queryGroup", queryGroup)
			.tag("queryType", queryType)
			.tag("dataSource", dataSource)
			.register(meterRegistry)
			.record(executionTime, java.util.concurrent.TimeUnit.MILLISECONDS);
	}

	private String determineQueryType(String sql) {
		if (sql.trim().toUpperCase().startsWith("SELECT")) return "SELECT";
		if (sql.trim().toUpperCase().startsWith("INSERT")) return "INSERT";
		if (sql.trim().toUpperCase().startsWith("UPDATE")) return "UPDATE";
		if (sql.trim().toUpperCase().startsWith("DELETE")) return "DELETE";
		return "UNKNOWN";
	}

}
