package com.ryuqq.core.storage.db;

import java.util.List;
import java.util.stream.Collectors;

import net.ttddyy.dsproxy.QueryInfo;

public class QueryGrouper {

	public static String groupQueries(List<QueryInfo> queryInfoList) {
		return queryInfoList.stream()
			.map(QueryInfo::getQuery)
			.map(QueryGrouper::extractQueryType) // 쿼리 유형 추출
			.distinct() // 중복 제거
			.collect(Collectors.joining(", ")); // 여러 유형을 콤마로 연결
	}

	private static String extractQueryType(String query) {
		if (query == null || query.isBlank()) {
			return "UNKNOWN";
		}
		String normalizedQuery = query.trim().toUpperCase();
		if (normalizedQuery.startsWith("SELECT")) {
			return "SELECT";
		} else if (normalizedQuery.startsWith("INSERT")) {
			return "INSERT";
		} else if (normalizedQuery.startsWith("UPDATE")) {
			return "UPDATE";
		} else if (normalizedQuery.startsWith("DELETE")) {
			return "DELETE";
		}
		return "OTHER";
	}


}
