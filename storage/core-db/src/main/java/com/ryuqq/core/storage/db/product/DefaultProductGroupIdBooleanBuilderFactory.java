package com.ryuqq.core.storage.db.product;

import com.querydsl.core.BooleanBuilder;

import com.ryuqq.core.domain.product.core.ProductGroupSearchCondition;
import com.ryuqq.core.storage.db.product.group.QProductGroupEntity;

public class DefaultProductGroupIdBooleanBuilderFactory {

	private static final QProductGroupEntity productGroup = QProductGroupEntity.productGroupEntity;

	public static BooleanBuilder buildBaseCondition(ProductGroupSearchCondition condition) {
		BooleanBuilder whereCondition = new BooleanBuilder();

		if (!condition.getSellerIds().isEmpty()) {
			whereCondition.and(productGroup.sellerId.in(condition.getSellerIds()));
		}

		if (!condition.getBrandIds().isEmpty()) {
			whereCondition.and(productGroup.brandId.in(condition.getBrandIds()));
		}

		if (!condition.getCategoryIds().isEmpty()) {
			whereCondition.and(productGroup.categoryId.in(condition.getCategoryIds()));
		}

		if (condition.getDisplayed() != null) {
			whereCondition.and(productGroup.displayed.eq(condition.getDisplayed()));
		}

		if (condition.getSoldOut() != null) {
			whereCondition.and(productGroup.soldOut.eq(condition.getSoldOut()));
		}

		if (condition.getCreatedAtFrom() != null && condition.getCreatedAtTo() != null) {
			whereCondition.and(productGroup.createdAt.between(condition.getCreatedAtFrom(), condition.getCreatedAtTo()));
		}

		return whereCondition;
	}

}
