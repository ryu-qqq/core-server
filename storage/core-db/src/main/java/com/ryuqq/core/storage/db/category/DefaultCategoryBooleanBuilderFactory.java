package com.ryuqq.core.storage.db.category;

import com.querydsl.core.BooleanBuilder;

import com.ryuqq.core.domain.category.core.CategorySearchCondition;

public class DefaultCategoryBooleanBuilderFactory {

	private static final QCategoryEntity categoryEntity = QCategoryEntity.categoryEntity;

	public static BooleanBuilder buildBaseCondition(CategorySearchCondition condition) {
		BooleanBuilder whereCondition = new BooleanBuilder();

		if (!condition.getCategoryIds().isEmpty()) {
			whereCondition.and(categoryEntity.id.in(condition.getCategoryIds()));
		}

		if (condition.getTargetGroup() != null) {
			whereCondition.and(categoryEntity.targetGroup.in(condition.getTargetGroup()));
		}

		if (condition.getCategoryType() != null) {
			whereCondition.and(categoryEntity.categoryType.in(condition.getCategoryType()));
		}


		whereCondition.and(CategorySearchConditionHelper.buildSearchCondition(condition));

		return whereCondition;
	}
}
