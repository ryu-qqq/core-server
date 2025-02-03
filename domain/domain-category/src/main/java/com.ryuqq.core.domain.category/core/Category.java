package com.ryuqq.core.domain.category.core;

import com.ryuqq.core.enums.CategoryType;
import com.ryuqq.core.enums.TargetGroup;

public interface Category {

	long getId();
	String getName();
	int getDepth();
	long getParentCategoryId();
	boolean getDisplayed();
	TargetGroup getTargetGroup();
	CategoryType getCategoryType();
	String getPath();
}
