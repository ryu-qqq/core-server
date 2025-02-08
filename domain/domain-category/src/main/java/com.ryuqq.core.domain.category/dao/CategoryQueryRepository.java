package com.ryuqq.core.domain.category;

import java.util.List;

public interface CategoryQueryRepository {

	boolean existById(long categoryId);
	List<DefaultCategory> fetchRecursiveByIds(List<Long> categoryIds, boolean isParentRelation);

}
