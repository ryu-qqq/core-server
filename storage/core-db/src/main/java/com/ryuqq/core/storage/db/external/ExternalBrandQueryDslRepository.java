package com.ryuqq.core.storage.db.external;

import static com.ryuqq.core.storage.db.external.QExternalBrandEntity.externalBrandEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ryuqq.core.storage.db.external.dto.ExternalBrandDto;
import com.ryuqq.core.storage.db.external.dto.QExternalBrandDto;

@Repository
public class ExternalBrandQueryDslRepository {

	private final JPAQueryFactory queryFactory;

	public ExternalBrandQueryDslRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	public Optional<ExternalBrandDto> fetchBySiteIdAndBrandId(long siteId, long brandId) {
		return Optional.ofNullable(
					queryFactory.select(
							new QExternalBrandDto(
								externalBrandEntity.siteId,
								externalBrandEntity.externalBrandId,
								externalBrandEntity.internalBrandId
							)
						)
						.from(externalBrandEntity)
						.where(siteIdEq(siteId), brandIdEq(brandId))
						.fetchOne()
		);
	}

	public List<ExternalBrandDto> fetchBySiteIdAndBrandIds(long siteId, List<Long> brandIds) {
		return queryFactory.select(
				new QExternalBrandDto(
					externalBrandEntity.siteId,
					externalBrandEntity.externalBrandId,
					externalBrandEntity.internalBrandId
				)
			)
			.from(externalBrandEntity)
			.where(siteIdEq(siteId), brandIdIn(brandIds))
			.fetch();
	}

	private BooleanExpression siteIdEq(long siteId){
		return externalBrandEntity.siteId.eq(siteId);
	}

	private BooleanExpression brandIdEq(long brandId){
		return externalBrandEntity.internalBrandId.eq(brandId);
	}


	private BooleanExpression brandIdIn(List<Long> brandIds){
		return (brandIds != null && !brandIds.isEmpty()) ? externalBrandEntity.internalBrandId.in(brandIds) : null;
	}


}
