package com.ryuqq.core.api.controller.v1.product.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.product.request.ProductInsertRequestDto;
import com.ryuqq.core.api.controller.v1.product.request.ProductOptionInsertRequestDto;
import com.ryuqq.core.domain.product.OptionContext;
import com.ryuqq.core.domain.product.Product;
import com.ryuqq.core.domain.product.ProductContext;
import com.ryuqq.core.domain.product.ProductContextBundle;
import com.ryuqq.core.domain.product.ProductGroupContext;

@Component
public class ProductMapper implements DomainMapper<List<ProductInsertRequestDto>>{

	@Override
	public boolean supports(Object fieldValue) {
		if (fieldValue instanceof List<?> list && !list.isEmpty()) {
			return list.getFirst() instanceof ProductInsertRequestDto;
		}
		return false;
	}

	@Override
	public ProductGroupContext.Builder map(List<ProductInsertRequestDto> source, ProductGroupContext.Builder builder) {
		List<ProductContext> productContexts = source.stream()
			.map(p -> new ProductContext(
						Product.create(
							p.productId(), null,
							p.soldOut(), p.displayed(),
							p.quantity(), p.additionalPrice(), false
						),
						toOptionContexts(p.options()),
				false
					)
			).toList();

		ProductContextBundle productContextBundle = new ProductContextBundle(productContexts);

		return builder.products(productContextBundle);
	}

	private List<OptionContext> toOptionContexts(List<ProductOptionInsertRequestDto> options) {
		return options.stream()
			.map(o -> OptionContext.create(o.optionName(), o.optionValue()))
			.distinct()
			.collect(Collectors.toList());
	}


}
