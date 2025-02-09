package com.ryuqq.core.storage.db.product.option;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.DefaultOptionContext;
import com.ryuqq.core.domain.product.DefaultProduct;
import com.ryuqq.core.domain.product.DefaultProductContext;
import com.ryuqq.core.domain.product.DefaultProductOptionContext;
import com.ryuqq.core.enums.OptionType;

@Component
public class ProductDomainMapper {

	public DefaultProductOptionContext toDomain(List<ProductContextDto> products) {
		if (products.isEmpty()) {
			return new DefaultProductOptionContext(0, OptionType.SINGLE, Collections.emptyList());
		}

		long productGroupId = products.getFirst().getProductGroupId();
		OptionType optionType = products.getFirst().getOptionType();
		List<DefaultProductContext> defaultProductContexts = createProductContexts(products);

		return new DefaultProductOptionContext(productGroupId, optionType, defaultProductContexts);
	}

	public List<DefaultProductOptionContext> toDomains(List<ProductContextDto> products) {
		if (products.isEmpty()) {
			return Collections.emptyList();
		}

		Map<String, List<ProductContextDto>> groupedByProductGroup = groupProductsByProductGroupId(products);

		return groupedByProductGroup.entrySet().stream()
			.map(entry -> {
				String[] split = entry.getKey().split(",");
				long productGroupId = Long.parseLong(split[0]);
				return new DefaultProductOptionContext(productGroupId, OptionType.valueOf(split[1]), createProductContexts(entry.getValue()));
			})
			.toList();
	}

	private Map<String, List<ProductContextDto>> groupProductsByProductGroupId(List<ProductContextDto> products) {
		return products.stream()
			.collect(Collectors.groupingBy(p -> p.getOptionGroupId() + "," + p.getOptionType()));
	}

	private List<DefaultProductContext> createProductContexts(List<ProductContextDto> products) {
		Map<Long, Set<DefaultOptionContext>> groupedOptions = groupOptionsByProductId(products);

		Map<Long, ProductContextDto> productMap = mapProductsById(products);

		return productMap.values().stream()
			.map(dto -> createProductContext(dto, groupedOptions))
			.toList();
	}

	private Map<Long, Set<DefaultOptionContext>> groupOptionsByProductId(List<ProductContextDto> products) {
		return products.stream()
			.filter(dto -> dto.getOptionName() != null)
			.collect(Collectors.groupingBy(
				ProductContextDto::getProductId,
				Collectors.mapping(
					this::createOptionContext,
					Collectors.toCollection(() -> new TreeSet<>(
						Comparator.comparing(DefaultOptionContext::getOptionName, Comparator.nullsFirst(Comparator.naturalOrder()))
					))
				)
			));
	}

	private Map<Long, ProductContextDto> mapProductsById(List<ProductContextDto> products) {
		return products.stream()
			.sorted(Comparator.comparing(ProductContextDto::getProductId))
			.collect(Collectors.toMap(
				ProductContextDto::getProductId,
				Function.identity(),
				(existing, replacement) -> existing
			));
	}

	private DefaultProductContext createProductContext(ProductContextDto dto, Map<Long, Set<DefaultOptionContext>> groupedOptions) {
		Long productId = dto.getProductId();
		List<DefaultOptionContext> options = new ArrayList<>(groupedOptions.getOrDefault(productId, Set.of()));

		return new DefaultProductContext(
			new DefaultProduct(
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

	private DefaultOptionContext createOptionContext(ProductContextDto dto) {
		return new DefaultOptionContext(
			dto.getProductOptionId(),
			dto.getOptionGroupId(),
			dto.getOptionDetailId(),
			dto.getProductId(),
			dto.getOptionName(),
			dto.getOptionValue(),
			false
		);
	}

}
