package com.ryuqq.core.api.controller.v1.category.mapper;

import java.util.List;

import com.ryuqq.core.domain.category.core.Category;
import com.ryuqq.core.enums.RequesterType;

public interface CategoryContextResponseMapper<T> {

	RequesterType getRequesterType();
	T toResponseDto(List<? extends Category> categories, int size, long count);

}
