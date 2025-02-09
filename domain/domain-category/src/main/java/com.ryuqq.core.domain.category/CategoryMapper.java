package com.ryuqq.core.domain.category;

import com.ryuqq.core.domain.category.dao.CategorySnapshot;

class CategoryMapper {

	static DefaultCategory toCategory(CategorySnapshot categorySnapshot){
		return new DefaultCategory(
			categorySnapshot.id(),
			categorySnapshot.categoryName(),
			categorySnapshot.depth(),
			categorySnapshot.parentCategoryId(),
			categorySnapshot.displayed(),
			categorySnapshot.targetGroup(),
			categorySnapshot.categoryType(),
			categorySnapshot.path()
		);
	}
}
