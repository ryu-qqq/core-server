package com.ryuqq.core.storage.db.external;

import static com.ryuqq.core.storage.db.external.QExternalCategoryEntity.externalCategoryEntity;
import static com.ryuqq.core.storage.db.external.QExternalCategoryOptionEntity.externalCategoryOptionEntity;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ryuqq.core.storage.db.external.dto.ExternalCategoryOptionDto;
import com.ryuqq.core.storage.db.external.dto.QExternalCategoryOptionDto;

@Repository
public class ExternalCategoryOptionQueryDslRepository {

	private final JPAQueryFactory queryFactory;

	public ExternalCategoryOptionQueryDslRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}


	public List<ExternalCategoryOptionDto> fetchBySiteIdAndCategoryIds(long siteId, List<String> externalCategoryIds){
		return queryFactory.select(
				new QExternalCategoryOptionDto(
					externalCategoryOptionEntity.siteId,
					externalCategoryOptionEntity.externalCategoryId,
					externalCategoryOptionEntity.optionGroupId,
					externalCategoryOptionEntity.optionId,
					externalCategoryOptionEntity.optionValue
				)
			)
			.from(externalCategoryOptionEntity)
			.innerJoin(externalCategoryEntity)
				.on(externalCategoryEntity.externalCategoryId.eq(externalCategoryOptionEntity.externalCategoryId))
			.where(
				siteIdEq(siteId),
				categoryIdIn(externalCategoryIds)
			).fetch();
	}

	private BooleanExpression categoryIdIn(List<String> externalCategoryIds){
		return (externalCategoryIds != null && !externalCategoryIds.isEmpty()) ? externalCategoryEntity.externalCategoryId.in(externalCategoryIds) : null;
	}

	private BooleanExpression siteIdEq(long siteId){
		return externalCategoryEntity.siteId.eq(siteId);
	}

}
