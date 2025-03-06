package com.ryuqq.core.api.controller.v1.brand.request;

import java.util.List;

import org.springframework.util.StringUtils;

import com.ryuqq.core.domain.brand.core.BrandSearchCondition;
import com.ryuqq.core.enums.BrandSearchFiled;
import com.ryuqq.core.enums.BrandSortField;
import com.ryuqq.core.enums.MainDisplayNameType;
import com.ryuqq.core.enums.Sort;

public record BrandSearchConditionRequestDto(
	Integer page,
	Integer size,
	List<Long> brandIds,
	Long cursorId,
	BrandSortField brandSortField,
	Sort sort,
	MainDisplayNameType mainDisplayNameType,
	BrandSearchFiled brandSearchFiled,
	String searchWord
) {

	public BrandSearchConditionRequestDto {
		validate();
	}

	private void validate() {
		if (brandSearchFiled == BrandSearchFiled.BRAND_NAME) {
			if (StringUtils.hasText(searchWord) && mainDisplayNameType == null) {
				throw new IllegalArgumentException("When searching by BRAND_NAME, mainDisplayNameType must be provided.");
			}
		}

		if (brandSearchFiled == BrandSearchFiled.ID) {
			if (StringUtils.hasText(searchWord) && !searchWord.matches("\\d+")) {
				throw new IllegalArgumentException("When searching by ID, searchWord must be a numeric value.");
			}
		}
	}

	public BrandSearchCondition toBrandSearchCondition() {
		return BrandSearchCondition.builder()
			.page(page != null ? page : 0)
			.size(size != null ? size : 20)
			.brandIds(brandIds != null ? brandIds : List.of())
			.brandSortField(brandSortField != null ? brandSortField : BrandSortField.ID)
			.sort(sort != null ? sort : Sort.DESC)
			.cursorId(cursorId)
			.mainDisplayNameType(mainDisplayNameType)
			.brandSearchFiled(brandSearchFiled)
			.searchWord(searchWord)
			.build();
	}
}
