package com.ryuqq.core.api.controller.v1.product.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.product.response.UserProductGroupContextResponseDto;
import com.ryuqq.core.api.payload.Slice;
import com.ryuqq.core.domain.brand.core.Brand;
import com.ryuqq.core.domain.category.core.Category;
import com.ryuqq.core.domain.product.core.ProductGroupContext;
import com.ryuqq.core.domain.seller.core.Seller;
import com.ryuqq.core.enums.RequesterType;

@Component
public class UserProductGroupContextResponseMapper implements ProductGroupContextResponseMapper<UserProductGroupContextResponseDto, Slice<UserProductGroupContextResponseDto>> {

	@Override
	public RequesterType getRequesterType() {
		return RequesterType.WEB;
	}

	@Override
	public UserProductGroupContextResponseDto toResponseDto(ProductGroupContext productGroupContext, Brand brand,
															List<? extends Category> categories, Seller seller) {
		return null;
	}

	@Override
	public Slice<UserProductGroupContextResponseDto> toResponseDto(
		List<? extends ProductGroupContext> productGroupContexts, Map<Long, Brand> brandMap,
		Map<Long, List<Category>> categories, Map<Long, Seller> sellerMap, int size, long count) {
		return null;
	}

}
