package com.ryuqq.core.api.controller.v1.category.response;

import com.ryuqq.core.enums.CategoryType;
import com.ryuqq.core.enums.TargetGroup;

public record DefaultCategoryContextResponseDto(
	long id,
	String categoryName,
	int depth,
	long parentCategoryId,
	boolean displayed,
	TargetGroup targetGroup,
	CategoryType categoryType,
	String path
)
	implements CategoryResponse{
}
