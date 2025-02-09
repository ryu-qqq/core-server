package com.ryuqq.core.storage.db.category;

import com.ryuqq.core.domain.category.dao.CategorySnapshot;
import com.ryuqq.core.storage.db.category.dto.CategoryDto;

public class CategorySnapshotMapper {

	public static CategorySnapshot toSnapshot(CategoryDto categoryDto) {
		return new CategorySnapshot(
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
