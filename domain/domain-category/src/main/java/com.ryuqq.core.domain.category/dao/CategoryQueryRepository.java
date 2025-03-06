package com.ryuqq.core.domain.category.dao;

import java.util.List;

import com.ryuqq.core.domain.category.core.CategorySearchCondition;

public interface CategoryQueryRepository {

	boolean existById(long categoryId);
	List<CategorySnapshot> fetchRecursiveByIds(List<Long> categoryIds, boolean isParentRelation);
	List<CategorySnapshot> fetchByCondition(CategorySearchCondition categorySearchCondition);
	long countByCondition(CategorySearchCondition categorySearchCondition);

}
