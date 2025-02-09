package com.ryuqq.core.domain.category.dao;

import java.util.List;

public interface CategoryQueryRepository {

	boolean existById(long categoryId);
	List<CategorySnapshot> fetchRecursiveByIds(List<Long> categoryIds, boolean isParentRelation);

}
