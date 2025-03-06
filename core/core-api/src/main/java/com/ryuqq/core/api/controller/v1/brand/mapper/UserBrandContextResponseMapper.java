package com.ryuqq.core.api.controller.v1.brand.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.brand.response.DefaultBrandContextResponseDto;
import com.ryuqq.core.domain.brand.core.Brand;
import com.ryuqq.core.enums.RequesterType;

@Component
public class UserBrandContextResponseMapper implements BrandContextResponseMapper<List<DefaultBrandContextResponseDto>> {

	@Override
	public RequesterType getRequesterType() {
		return RequesterType.WEB;
	}

	@Override
	public List<DefaultBrandContextResponseDto> toResponseDto(List<? extends Brand> brands, int size, long count) {
		return List.of();
	}
}
