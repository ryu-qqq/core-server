package com.ryuqq.core.api.controller.v1.product.mapper;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.product.request.ProductNoticeInsertRequestDto;
import com.ryuqq.core.domain.product.ProductGroupContext;
import com.ryuqq.core.domain.product.ProductNotice;

@Component
public class ProductNoticeMapper implements DomainMapper<ProductNoticeInsertRequestDto> {

	@Override
	public boolean supports(Object fieldValue) {
		return fieldValue instanceof ProductNoticeInsertRequestDto;
	}

	@Override
	public ProductGroupContext.Builder map(ProductNoticeInsertRequestDto source, ProductGroupContext.Builder builder) {
		ProductNotice productNotice = ProductNotice.create(
			source.material(),
			source.color(),
			source.size(),
			source.maker(),
			source.origin(),
			source.washingMethod(),
			source.yearMonth(),
			source.assuranceStandard(),
			source.asPhone()
		);

		builder.productNotice(productNotice);
		return builder;
	}
}
