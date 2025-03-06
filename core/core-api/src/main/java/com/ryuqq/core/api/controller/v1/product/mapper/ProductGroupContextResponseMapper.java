package com.ryuqq.core.api.controller.v1.product.mapper;

import java.util.List;
import java.util.Map;

import com.ryuqq.core.api.controller.v1.product.response.ProductGroupContextResponse;
import com.ryuqq.core.domain.brand.core.Brand;
import com.ryuqq.core.domain.category.core.Category;
import com.ryuqq.core.domain.product.core.ProductGroupContext;
import com.ryuqq.core.domain.seller.core.Seller;
import com.ryuqq.core.enums.RequesterType;

public interface ProductGroupContextResponseMapper<T extends ProductGroupContextResponse, C> {

	RequesterType getRequesterType();
	T toResponseDto(ProductGroupContext productGroupContext, Brand brand, List<? extends Category> categories, Seller seller);
	C toResponseDto(List<? extends ProductGroupContext> productGroupContexts, Map<Long, Brand> brandMap, Map<Long, List<Category>> categories, Map<Long, Seller> sellerMap, int size, long count);

}
