package com.ryuqq.core.storage.db.category;

import com.querydsl.core.types.OrderSpecifier;

import com.ryuqq.core.enums.CategorySortField;
import com.ryuqq.core.enums.Sort;

public class CategorySortFieldOrderResolver {

	private static final QCategoryEntity categoryEntity = QCategoryEntity.categoryEntity;

	public static OrderSpecifier<?> resolveSortOrder(CategorySortField sortField, Sort sort) {
		if(sort.isAsc()) return categoryEntity.id.asc();
		else return categoryEntity.id.desc();
	}



}
