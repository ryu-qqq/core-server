package com.ryuqq.core.api.controller.v1.category.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ryuqq.core.api.controller.v1.category.mapper.CategoryContextResponseMapperProvider;
import com.ryuqq.core.api.controller.v1.category.request.CategorySearchConditionRequestDto;
import com.ryuqq.core.domain.category.core.Category;
import com.ryuqq.core.domain.category.core.CategoryQueryInterface;
import com.ryuqq.core.domain.category.core.CategorySearchCondition;
import com.ryuqq.core.enums.RequesterType;

@Service
public class CategoryContextQueryService {

	private final CategoryContextResponseMapperProvider brandContextResponseMapperProvider;
	private final CategoryQueryInterface categoryQueryInterface;

	public CategoryContextQueryService(CategoryContextResponseMapperProvider brandContextResponseMapperProvider,
									   CategoryQueryInterface categoryQueryInterface) {
		this.brandContextResponseMapperProvider = brandContextResponseMapperProvider;
		this.categoryQueryInterface = categoryQueryInterface;
	}

	public <T> T fetchByConditionForRequester(
		CategorySearchConditionRequestDto categorySearchConditionRequestDto, RequesterType requesterType){
		CategorySearchCondition categorySearchCondition = categorySearchConditionRequestDto.toCategorySearchRequest();

		long count = categoryQueryInterface.countByCondition(categorySearchCondition);

		if(count ==0) return (T) List.of();

		List<? extends Category> categories = categoryQueryInterface.fetchByCondition(categorySearchCondition);

		return (T) brandContextResponseMapperProvider
			.getMapper(requesterType)
			.toResponseDto(
				categories,
				categorySearchCondition.getSize(),
				count
			);
	}

}
