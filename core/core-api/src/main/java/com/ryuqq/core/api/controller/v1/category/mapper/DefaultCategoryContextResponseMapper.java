package com.ryuqq.core.api.controller.v1.category.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.category.response.DefaultCategoryContextResponseDto;
import com.ryuqq.core.api.payload.Slice;
import com.ryuqq.core.domain.category.core.Category;
import com.ryuqq.core.enums.RequesterType;

@Component
public class DefaultCategoryContextResponseMapper implements CategoryContextResponseMapper<Slice<DefaultCategoryContextResponseDto>> {

	private final DefaultCategoryContextResponseSliceMapper defaultCategoryContextResponseSliceMapper;

	public DefaultCategoryContextResponseMapper(
		DefaultCategoryContextResponseSliceMapper defaultCategoryContextResponseSliceMapper) {
		this.defaultCategoryContextResponseSliceMapper = defaultCategoryContextResponseSliceMapper;
	}

	@Override
	public RequesterType getRequesterType() {
		return RequesterType.DEFAULT;
	}

	@Override
	public Slice<DefaultCategoryContextResponseDto> toResponseDto(List<? extends Category> categories, int size,
																  long count) {

		List<DefaultCategoryContextResponseDto> responseDtos = categories.stream()
			.map(c -> new DefaultCategoryContextResponseDto(
						c.getId(),
						c.getName(),
						c.getDepth(),
						c.getParentCategoryId(),
						c.getDisplayed(),
						c.getTargetGroup(),
						c.getCategoryType(),
						c.getPath()
						)
			)
			.toList();

		return defaultCategoryContextResponseSliceMapper.toSlice(responseDtos, size, count);
	}

}
