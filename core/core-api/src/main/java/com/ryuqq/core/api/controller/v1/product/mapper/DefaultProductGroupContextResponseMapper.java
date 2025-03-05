package com.ryuqq.core.api.controller.v1.product.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.product.response.DefaultProductGroupContextResponseDto;
import com.ryuqq.core.api.payload.Slice;
import com.ryuqq.core.domain.brand.core.Brand;
import com.ryuqq.core.domain.category.core.Category;
import com.ryuqq.core.domain.product.core.ProductGroup;
import com.ryuqq.core.domain.product.core.ProductGroupContext;
import com.ryuqq.core.domain.seller.core.Seller;
import com.ryuqq.core.enums.RequesterType;

@Component
public class DefaultProductGroupContextResponseMapper implements ProductGroupContextResponseMapper<DefaultProductGroupContextResponseDto, Slice<DefaultProductGroupContextResponseDto>> {

	private final DefaultProductGroupContextResponseSliceMapper defaultProductGroupContextResponseSliceMapper;

	public DefaultProductGroupContextResponseMapper(
		DefaultProductGroupContextResponseSliceMapper defaultProductGroupContextResponseSliceMapper) {
		this.defaultProductGroupContextResponseSliceMapper = defaultProductGroupContextResponseSliceMapper;
	}

	@Override
	public RequesterType getRequesterType() {
		return RequesterType.DEFAULT;
	}

	@Override
	public DefaultProductGroupContextResponseDto toResponseDto(ProductGroupContext productGroupContext, Brand brand,
															   List<? extends Category> categories, Seller seller) {
		return new DefaultProductGroupContextResponseDto(productGroupContext, brand, categories, seller);
	}

	@Override
	public Slice<DefaultProductGroupContextResponseDto> toResponseDto(
		List<? extends ProductGroupContext> productGroupContexts, Map<Long, Brand> brandMap,
		Map<Long, List<Category>> categoryMap, Map<Long, Seller> sellerMap, int size, long count) {


		List<DefaultProductGroupContextResponseDto> responseDtos = new ArrayList<>();

		for(ProductGroupContext productGroupContext : productGroupContexts) {
			ProductGroup productGroup = productGroupContext.getProductGroup();
			Brand brand = brandMap.get(productGroup.getBrandId());
			List<Category> categories = categoryMap.get(productGroup.getCategoryId());
			Seller seller = sellerMap.get(productGroup.getSellerId());

			DefaultProductGroupContextResponseDto defaultProductGroupContextResponseDto = new DefaultProductGroupContextResponseDto(
				productGroupContext, brand, categories, seller);

			responseDtos.add(defaultProductGroupContextResponseDto);
		}

		return defaultProductGroupContextResponseSliceMapper.toSlice(responseDtos, size, count);
	}


}
