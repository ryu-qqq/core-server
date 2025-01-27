package com.ryuqq.core.api.controller.v1.product.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.product.request.ProductGroupImageRequestDto;
import com.ryuqq.core.domain.product.ProductGroupContext;
import com.ryuqq.core.domain.product.ProductGroupImage;
import com.ryuqq.core.domain.product.ProductGroupImageBundle;

@Component
public class ProductGroupImageMapper implements DomainMapper<List<ProductGroupImageRequestDto>> {

	@Override
	public boolean supports(Object fieldValue) {
		if (fieldValue instanceof List<?> list && !list.isEmpty()) {
			return list.getFirst() instanceof ProductGroupImageRequestDto;
		}
		return false;
	}


	@Override
	public ProductGroupContext.Builder map(List<ProductGroupImageRequestDto> source,
										   ProductGroupContext.Builder builder) {

		List<ProductGroupImage> productGroupImages = source.stream()
			.map(p -> ProductGroupImage.create(
				p.productImageType(),
				p.imageUrl(),
				p.imageUrl(),
				false
			)).toList();

		ProductGroupImageBundle productGroupImageBundle = new ProductGroupImageBundle(productGroupImages);

		return builder.productGroupImages(productGroupImageBundle);
	}


}
