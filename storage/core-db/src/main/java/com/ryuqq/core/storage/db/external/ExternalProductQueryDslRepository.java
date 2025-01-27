package com.ryuqq.core.storage.db.external;

import static com.ryuqq.core.storage.db.external.QExternalProductEntity.externalProductEntity;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ryuqq.core.storage.db.external.dto.ExternalProductDto;
import com.ryuqq.core.storage.db.external.dto.QExternalProductDto;

@Repository
public class ExternalProductQueryDslRepository  {

	private final JPAQueryFactory queryFactory;

	public ExternalProductQueryDslRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	public List<ExternalProductDto> fetchByExternalProductGroupId(String externalProductGroupId) {
		return queryFactory.select(
				new QExternalProductDto(
					externalProductEntity.id,
					externalProductEntity.externalProductGroupId,
					externalProductEntity.externalProductId,
					externalProductEntity.productId,
					externalProductEntity.optionValue,
					externalProductEntity.quantity,
					externalProductEntity.additionalPrice,
					externalProductEntity.soldOut,
					externalProductEntity.displayed
				)
			)
			.from(externalProductEntity)
			.where(
				externalProductGroupIdEq(externalProductGroupId),
				notDeleted()
			)
			.fetch();
	}


	public List<ExternalProductDto> fetchByExternalProductGroupIds(List<String> externalProductGroupIds) {
		return queryFactory.select(
				new QExternalProductDto(
					externalProductEntity.id,
					externalProductEntity.externalProductGroupId,
					externalProductEntity.externalProductId,
					externalProductEntity.productId,
					externalProductEntity.optionValue,
					externalProductEntity.quantity,
					externalProductEntity.additionalPrice,
					externalProductEntity.soldOut,
					externalProductEntity.displayed
				)
			)
			.from(externalProductEntity)
			.where(
				externalProductGroupIdIn(externalProductGroupIds),
				notDeleted()
			)
			.fetch();
	}

	private BooleanExpression externalProductGroupIdEq(String externalProductGroupId) {
		return externalProductEntity.externalProductGroupId.eq(externalProductGroupId);
	}

	private BooleanExpression externalProductGroupIdIn(List<String> externalProductGroupIds) {
		return externalProductEntity.externalProductGroupId.in(externalProductGroupIds);
	}

	private BooleanExpression notDeleted() {
		return externalProductEntity.deleted.eq(false);
	}
}
