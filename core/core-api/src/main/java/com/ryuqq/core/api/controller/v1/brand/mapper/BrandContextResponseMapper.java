package com.ryuqq.core.api.controller.v1.brand.mapper;

import java.util.List;

import com.ryuqq.core.domain.brand.core.Brand;
import com.ryuqq.core.enums.RequesterType;

public interface BrandContextResponseMapper<T> {

	RequesterType getRequesterType();
	T toResponseDto(List<? extends Brand> brands, int size, long count);

}
