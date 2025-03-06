package com.ryuqq.core.api.data;

import java.util.ArrayList;
import java.util.List;

import com.ryuqq.core.api.controller.v1.category.response.DefaultCategoryContextResponseDto;
import com.ryuqq.core.domain.category.DefaultCategory;
import com.ryuqq.core.domain.category.core.Category;
import com.ryuqq.core.enums.CategoryType;
import com.ryuqq.core.enums.TargetGroup;

public class CategoryModuleHelper {

	public static List<Category> createCategories(){

		List<Category> categories = new ArrayList<>();

		categories.add(new DefaultCategory( 1543, "kids",1, 0L, true,  TargetGroup.FEMALE, CategoryType.NONE,"1543"));
		categories.add(new DefaultCategory( 1544, "의류", 2,1543, true,  TargetGroup.KIDS,CategoryType.CLOTHING,"1543,1544"));
		categories.add(new DefaultCategory( 1545, "탑", 3,1544, true,   TargetGroup.KIDS, CategoryType.CLOTHING,"1543,1544,1545"));
		categories.add(new DefaultCategory( 1549, "반팔티셔츠", 4,1545, true,  TargetGroup.KIDS, CategoryType.CLOTHING,"1543,1544,1545,1549"));

		return categories;
	}



	public static List<DefaultCategoryContextResponseDto> createCategoryContextResponseDtos(){
		return createCategories().stream()
			.map(c -> new DefaultCategoryContextResponseDto(
				c.getId(),
				c.getName(),
				c.getDepth(),
				c.getParentCategoryId(),
				c.getDisplayed(),
				c.getTargetGroup(),
				c.getCategoryType(),
				c.getPath()
			)).toList();
	}

}
