package com.ryuqq.core.domain.category;

import com.ryuqq.core.domain.category.core.Category;
import com.ryuqq.core.enums.CategoryType;
import com.ryuqq.core.enums.TargetGroup;

record DefaultCategory(
	 long id,
	 String categoryName,
	 int depth,
	 long parentCategoryId,
	 boolean displayed,
	 TargetGroup targetGroup,
	 CategoryType categoryType,
	 String path
) implements Category {
	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getName() {
		return categoryName;
	}

	@Override
	public int getDepth() {
		return depth;
	}

	@Override
	public long getParentCategoryId() {
		return parentCategoryId;
	}

	@Override
	public boolean getDisplayed() {
		return displayed;
	}

	@Override
	public TargetGroup getTargetGroup() {
		return targetGroup;
	}

	@Override
	public CategoryType getCategoryType() {
		return categoryType;
	}

	@Override
	public String getPath() {
		return path;
	}
}
