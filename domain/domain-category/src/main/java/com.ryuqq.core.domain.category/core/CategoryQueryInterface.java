package com.ryuqq.core.domain.category.core;

import java.util.List;

public interface CategoryQueryInterface {

	boolean existById(long id);
	List<? extends Category> fetchRecursiveByIds(long categoryId, boolean isParentRelation);
	List<? extends Category> fetchRecursiveByIds(List<Long> categoryIds, boolean isParentRelation);
	List<? extends Category> fetchByCondition(CategorySearchCondition categorySearchCondition);
	long countByCondition(CategorySearchCondition categorySearchCondition);

}
