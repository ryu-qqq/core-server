package com.ryuqq.core.storage.db.category;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.category.DefaultCategory;

@Component
public class CategoryDomainMapper {

	public DefaultCategory toDomain(CategoryDto categoryDto) {
		return new DefaultCategory(
			categoryDto.getId(),
			categoryDto.getCategoryName(),
			categoryDto.getDepth(),
			categoryDto.getParentCategoryId(),
			categoryDto.isDisplayed(),
			categoryDto.getTargetGroup(),
			categoryDto.getCategoryType(),
			categoryDto.getPath()
		);

	}
}
