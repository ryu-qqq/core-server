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

	public void collect(List<QueryInfo> queryInfoList, long executionTime) {
		String queryGroup = QueryGrouper.groupQueries(queryInfoList); // 쿼리 유형별 그룹화
		Timer.builder("db.query.execution.time")
			.tag("queryGroup", queryGroup) // 정규화된 쿼리 그룹 태그 추가
			.register(meterRegistry)
			.record(executionTime, java.util.concurrent.TimeUnit.MILLISECONDS); // 실행 시간 기록
	}

}
