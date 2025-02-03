package com.ryuqq.core.storage.db.external;

import static com.ryuqq.core.storage.db.external.QExternalCategoryEntity.externalCategoryEntity;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ryuqq.core.storage.db.external.dto.ExternalCategoryDto;
import com.ryuqq.core.storage.db.external.dto.QExternalCategoryDto;

@Repository
public class ExternalCategoryQueryDslRepository  {

	private final JPAQueryFactory queryFactory;

	public ExternalCategoryQueryDslRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}


	public List<ExternalCategoryDto> fetchBySiteIdAndCategoryIds(long siteId, List<Long> categoryIds){
		return queryFactory.select(
				new QExternalCategoryDto(
					externalCategoryEntity.siteId,
					externalCategoryEntity.externalCategoryId,
					externalCategoryEntity.externalCategoryExtraId.coalesce(""),
					externalCategoryEntity.description.coalesce(""),
					externalCategoryEntity.internalCategoryId
				)
			)
			.from(externalCategoryEntity)
			.where( siteIdEq(siteId), categoryIdIn(categoryIds))
			.fetch();
	}


	private BooleanExpression categoryIdIn(List<Long> categoryIds){
		return (categoryIds != null && !categoryIds.isEmpty()) ? externalCategoryEntity.internalCategoryId.in(categoryIds) : null;
	}

	private BooleanExpression siteIdEq(long siteId){
		return externalCategoryEntity.siteId.eq(siteId);
	}

}
