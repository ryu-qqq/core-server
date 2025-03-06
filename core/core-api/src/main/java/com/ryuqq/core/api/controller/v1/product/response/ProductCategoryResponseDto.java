package com.ryuqq.core.api.controller.v1.product.response;

import com.ryuqq.core.domain.category.core.Category;
import com.ryuqq.core.enums.CategoryType;
import com.ryuqq.core.enums.TargetGroup;

public record ProductCategoryResponseDto(
	long id,
	String name,
	int depth,
	long parentCategoryId,
	boolean displayed,
	TargetGroup targetGroup,
	CategoryType categoryType,
	String path
) {


	public static ProductCategoryResponseDto from(Category category){
		return new ProductCategoryResponseDto(
			category.getId(),
			category.getName(),
			category.getDepth(),
			category.getParentCategoryId(),
			category.getDisplayed(),
			category.getTargetGroup(),
			category.getCategoryType(),
			category.getPath()
		);
	}

}
