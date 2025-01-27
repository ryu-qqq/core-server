package com.ryuqq.core.storage.db.product.option;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.OptionContext;
import com.ryuqq.core.domain.product.Product;
import com.ryuqq.core.domain.product.ProductContext;
import com.ryuqq.core.domain.product.ProductContextBundle;

@Component
public class ProductDomainMapper {

	public ProductContextBundle toDomains(List<ProductContextDto> products) {
		Map<Long, Set<OptionContext>> groupedOptions = groupOptionsByProductId(products);

		Map<Long, ProductContextDto> productMap = mapProductsById(products);

		List<ProductContext> productContexts = productMap.values().stream()
			.map(dto -> createProductContext(dto, groupedOptions))
			.sorted(Comparator.comparing(ProductContext::getId))
			.toList();

		return new ProductContextBundle(productContexts);
	}

	private Map<Long, Set<OptionContext>> groupOptionsByProductId(List<ProductContextDto> products) {
		return products.stream()
			.filter(dto -> dto.getOptionName() != null)
			.collect(Collectors.groupingBy(
				ProductContextDto::getProductId,
				Collectors.mapping(
					this::createOptionContext,
					Collectors.toCollection(() -> new TreeSet<>(
						Comparator.comparing(OptionContext::getOptionName, Comparator.nullsFirst(Comparator.naturalOrder()))
					))
				)
			));
	}

	private Map<Long, ProductContextDto> mapProductsById(List<ProductContextDto> products) {
		return products.stream()
			.collect(Collectors.toMap(
				ProductContextDto::getProductId,
				Function.identity(),
				(existing, replacement) -> existing
			));
	}

	private ProductContext createProductContext(ProductContextDto dto, Map<Long, Set<OptionContext>> groupedOptions) {
		Long productId = dto.getProductId();
		List<OptionContext> options = new ArrayList<>(groupedOptions.getOrDefault(productId, Set.of()));

		return new ProductContext(
			Product.create(
				dto.getProductId(),
				dto.getProductGroupId(),
				dto.isSoldOutYn(),
				dto.isDisplayYn(),
				dto.getQuantity(),
				dto.getAdditionalPrice(),
				dto.isProductDeleted()
			),
			options,
			false
		);
	}

	private OptionContext createOptionContext(ProductContextDto dto) {
		return OptionContext.create(
			dto.getProductOptionId(),
			dto.getOptionGroupId(),
			dto.getOptionDetailId(),
			dto.getProductId(),
			dto.getOptionName(),
			dto.getOptionValue()
		);
	}

}
