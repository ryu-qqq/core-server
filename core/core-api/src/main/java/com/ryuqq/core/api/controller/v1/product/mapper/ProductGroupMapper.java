package com.ryuqq.core.api.controller.v1.product.mapper;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.product.request.ProductGroupInsertRequestDto;
import com.ryuqq.core.domain.product.ProductGroup;
import com.ryuqq.core.domain.product.ProductGroupContext;
import com.ryuqq.core.enums.ProductStatus;

@Component
public class ProductGroupMapper implements DomainMapper<ProductGroupInsertRequestDto> {

	@Override
	public boolean supports(Object fieldValue) {
		return fieldValue instanceof ProductGroupInsertRequestDto;
	}

	@Override
	public ProductGroupContext.Builder map(ProductGroupInsertRequestDto source, ProductGroupContext.Builder builder) {
		ProductGroup productGroup = ProductGroup.create(
			source.productGroupId(),
			source.sellerId(),
			source.categoryId(),
			source.brandId(),
			source.productGroupName(),
			source.styleCode(),
			source.productCondition(),
			source.managementType(),
			source.optionType(),
			source.regularPrice(),
			source.currentPrice(),
			source.soldOut(),
			source.displayed(),
			ProductStatus.WAITING,
			source.keywords()
		);

		builder.productGroup(productGroup);
		return builder;
	}



}
