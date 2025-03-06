package com.ryuqq.core.storage.db.brand;

import com.querydsl.core.types.OrderSpecifier;

import com.ryuqq.core.enums.BrandSortField;
import com.ryuqq.core.enums.Sort;

public class BrandSortFieldOrderResolver {

	private static final QBrandEntity brandEntity = QBrandEntity.brandEntity;

	public static OrderSpecifier<?> resolveSortOrder(BrandSortField sortField, Sort sort) {
		if(sort.isAsc()) return brandEntity.id.asc();
		else return brandEntity.id.desc();
	}



}
