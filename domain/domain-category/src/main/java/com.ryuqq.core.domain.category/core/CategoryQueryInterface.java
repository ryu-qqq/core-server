package com.ryuqq.core.domain.category.core;

import java.util.List;

import com.ryuqq.core.domain.category.DefaultCategory;

public interface CategoryQueryInterface {

	List<DefaultCategory> fetchRecursiveByIds(long categoryId, boolean isParentRelation);
}
