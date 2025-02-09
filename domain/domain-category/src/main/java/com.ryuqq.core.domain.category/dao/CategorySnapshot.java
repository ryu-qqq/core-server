package com.ryuqq.core.domain.category.dao;

import com.ryuqq.core.enums.CategoryType;
import com.ryuqq.core.enums.TargetGroup;

public record CategorySnapshot(
	long id,
	String categoryName,
	int depth,
	long parentCategoryId,
	boolean displayed,
	TargetGroup targetGroup,
	CategoryType categoryType,
	String path
) {
}
