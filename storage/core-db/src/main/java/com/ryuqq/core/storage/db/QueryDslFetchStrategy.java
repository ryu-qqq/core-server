package com.ryuqq.core.storage.db;

import java.util.function.Function;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.NumberPath;

import com.ryuqq.core.enums.Sort;

public class QueryDslFetchStrategy<T> {
	private final boolean useCursorPaging;
	private final Long cursorId;
	private final int limitSize;
	private final int page;
	private final OrderSpecifier<Long> sortOrder;
	private final Predicate whereCondition;

	private QueryDslFetchStrategy(boolean useCursorPaging, Long cursorId, int limitSize, int page,
								  OrderSpecifier<Long> sortOrder, Predicate whereCondition) {
		this.useCursorPaging = useCursorPaging;
		this.cursorId = cursorId;
		this.limitSize = limitSize;
		this.page = page;
		this.sortOrder = sortOrder;
		this.whereCondition = whereCondition;
	}

	public static <T> QueryDslFetchStrategy<T> create(
		Long cursorId, int size, int page, Sort sort,
		EntityPath<T> entityPath, Function<EntityPath<T>, NumberPath<Long>> idExtractor,
		Predicate whereCondition) {
		return new QueryDslFetchStrategy<>(
			cursorId != null,
			cursorId,
			size, page,
			resolveSortOrder(sort, entityPath, idExtractor),
			whereCondition
		);
	}

	public boolean isUseCursorPaging() {
		return useCursorPaging;
	}

	public Long getCursorId() {
		return cursorId;
	}

	public int getLimitSize() {
		return limitSize;
	}

	public int getPage() {
		return page;
	}

	public OrderSpecifier<Long> getSortOrder() {
		return sortOrder;
	}

	public Predicate getWhereCondition() {
		return whereCondition;
	}

	private static <T> OrderSpecifier<Long> resolveSortOrder(Sort sort, EntityPath<T> entityPath,
															 Function<EntityPath<T>, NumberPath<Long>> idExtractor) {
		NumberPath<Long> idPath = idExtractor.apply(entityPath);
		return sort == Sort.ASC ? idPath.asc() : idPath.desc();
	}


}
