package com.ryuqq.core.api.controller.v1.brand.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.brand.response.DefaultBrandContextResponseDto;
import com.ryuqq.core.api.payload.Slice;
import com.ryuqq.core.domain.brand.core.Brand;
import com.ryuqq.core.enums.RequesterType;

@Component
public class DefaultBrandContextResponseMapper implements BrandContextResponseMapper<Slice<DefaultBrandContextResponseDto>> {

	private final DefaultBrandContextResponseSliceMapper defaultBrandContextResponseSliceMapper;

	public DefaultBrandContextResponseMapper(
		DefaultBrandContextResponseSliceMapper defaultBrandContextResponseSliceMapper) {
		this.defaultBrandContextResponseSliceMapper = defaultBrandContextResponseSliceMapper;
	}

	@Override
	public RequesterType getRequesterType() {
		return RequesterType.DEFAULT;
	}

	@Override
	public Slice<DefaultBrandContextResponseDto> toResponseDto(List<? extends Brand> brands, int size,
															   long count) {

		List<DefaultBrandContextResponseDto> responseDtos = brands.stream().map(b -> new DefaultBrandContextResponseDto(
				b.id(),
				b.brandName(),
				b.brandNameKr(),
				b.displayed()
			))
			.toList();

		return defaultBrandContextResponseSliceMapper.toSlice(responseDtos, size, count);
	}

}
