package com.ryuqq.core.api.controller.v1.category.request;

import java.util.List;

import org.springframework.util.StringUtils;

import com.ryuqq.core.domain.category.core.CategorySearchCondition;
import com.ryuqq.core.enums.CategorySearchFiled;
import com.ryuqq.core.enums.CategorySortField;
import com.ryuqq.core.enums.CategoryType;
import com.ryuqq.core.enums.Sort;
import com.ryuqq.core.enums.TargetGroup;

public record CategorySearchConditionRequestDto(
	Integer page,
	Integer size,
	Long cursorId,
	List<Long> categoryIds,
	CategoryType categoryType,
	TargetGroup targetGroup,
	CategorySortField categorySortField,
	Sort sort,
	CategorySearchFiled categorySearchFiled,
	String searchWord
) {

	public CategorySearchConditionRequestDto {
		validate();
	}

	private void validate() {
		if (categorySearchFiled == CategorySearchFiled.CATEGORY_NAME && !StringUtils.hasText(searchWord)) {
			throw new IllegalArgumentException("When searching by CATEGORY_NAME, searchWord must be provided.");
		}

		if (categorySearchFiled == CategorySearchFiled.ID) {
			if (StringUtils.hasText(searchWord) && !searchWord.matches("\\d+")) {
				throw new IllegalArgumentException("When searching by ID, searchWord must be a numeric value.");
			}
		}
	}

	public CategorySearchCondition toCategorySearchRequest(){
		return CategorySearchCondition.builder()
			.page(page != null ? page : 0)
			.size(size != null ? size : 20)
			.categoryIds(categoryIds != null ? categoryIds : List.of())
			.categoryType(categoryType)
			.targetGroup(targetGroup)
			.categorySortField(categorySortField != null ? categorySortField : CategorySortField.ID)
			.sort(sort != null ? sort : Sort.DESC)
			.cursorId(cursorId)
			.categorySearchFiled(categorySearchFiled)
			.searchWord(searchWord)
			.build();
	}
}
