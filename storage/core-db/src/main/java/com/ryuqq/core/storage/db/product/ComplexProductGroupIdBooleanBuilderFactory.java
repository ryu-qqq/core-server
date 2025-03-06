package com.ryuqq.core.storage.db.product;

import com.querydsl.core.BooleanBuilder;

import com.ryuqq.core.domain.product.core.ProductGroupSearchCondition;
import com.ryuqq.core.storage.db.product.group.QProductGroupEntity;

public class ComplexProductGroupIdBooleanBuilderFactory {

	private static final QProductGroupEntity productGroup = QProductGroupEntity.productGroupEntity;

	public static BooleanBuilder buildComplexCondition(ProductGroupSearchCondition condition) {
		BooleanBuilder whereCondition = DefaultProductGroupIdBooleanBuilderFactory.buildBaseCondition(condition);

		if (condition.getMinSalePrice() != null) {
			whereCondition.and(productGroup.salePrice.goe(condition.getMinSalePrice()));
		}

		if (condition.getMaxSalePrice() != null) {
			whereCondition.and(productGroup.salePrice.loe(condition.getMaxSalePrice()));
		}

		if (condition.getMinDiscountRate() != null) {
			whereCondition.and(productGroup.discountRate.goe(condition.getMinDiscountRate()));
		}

		if (condition.getMaxDiscountRate() != null) {
			whereCondition.and(productGroup.discountRate.loe(condition.getMaxDiscountRate()));
		}

		whereCondition.and(ProductGroupSearchConditionHelper.buildSearchCondition(condition));

		return whereCondition;
	}


}
