package com.ryuqq.core.storage.db.brand;

import com.querydsl.core.BooleanBuilder;

import com.ryuqq.core.domain.brand.core.BrandSearchCondition;

public class DefaultBrandBooleanBuilderFactory {

	private static final QBrandEntity brandEntity = QBrandEntity.brandEntity;

	public static BooleanBuilder buildBaseCondition(BrandSearchCondition condition) {
		BooleanBuilder whereCondition = new BooleanBuilder();

		if (!condition.getBrandIds().isEmpty()) {
			whereCondition.and(brandEntity.id.in(condition.getBrandIds()));
		}

		whereCondition.and(BrandSearchConditionHelper.buildSearchCondition(condition));

		return whereCondition;
	}
}
