package com.ryuqq.core.storage.db.product;

import com.querydsl.core.types.OrderSpecifier;

import com.ryuqq.core.enums.ProductSortField;
import com.ryuqq.core.enums.Sort;
import com.ryuqq.core.storage.db.product.group.QProductGroupEntity;

public class ProductSortFieldOrderResolver {

	private static final QProductGroupEntity productGroup = QProductGroupEntity.productGroupEntity;

	public static OrderSpecifier<?> resolveSortOrder(ProductSortField sortField, Sort sort) {
		return switch (sortField) {
			case PRICE -> sort.isAsc() ?
				productGroup.salePrice.asc() :
				productGroup.salePrice.desc();
			case DISCOUNT -> sort.isAsc() ?
				productGroup.discountRate.asc() :
				productGroup.discountRate.desc();
			default -> sort.isAsc() ?
				productGroup.id.asc() :
				productGroup.id.desc();
		};
	}

}
