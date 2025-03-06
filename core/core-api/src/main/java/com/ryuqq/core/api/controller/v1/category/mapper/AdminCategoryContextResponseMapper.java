package com.ryuqq.core.api.controller.v1.category.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.category.response.UserCategoryContextResponseDto;
import com.ryuqq.core.domain.category.core.Category;
import com.ryuqq.core.enums.RequesterType;

@Component
public class AdminCategoryContextResponseMapper implements CategoryContextResponseMapper<List<UserCategoryContextResponseDto>>{

	@Override
	public RequesterType getRequesterType() {
		return RequesterType.ADMIN;
	}

	@Override
	public List<UserCategoryContextResponseDto> toResponseDto(List<? extends Category> categories, int size,
															  long count) {
		return List.of();
	}
}
