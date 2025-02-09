package com.ryuqq.core.domain.product;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class ProductGroupContextAssembler {

	private final ProductGroupFinder productGroupFinder;
	private final ProductFinder productFinder;

	public ProductGroupContextAssembler(ProductGroupFinder productGroupFinder, ProductFinder productFinder) {
		this.productGroupFinder = productGroupFinder;
		this.productFinder = productFinder;
	}

	public DefaultProductGroupContext assemble(long productGroupId) {
		DefaultProductGroupContext defaultProductGroupContext = productGroupFinder.fetchNoProductContextById(productGroupId);
		DefaultProductOptionContext defaultProductOptionContext = productFinder.fetchByProductGroupId(productGroupId);
		return DefaultProductGroupContext.builder()
			.productGroup(defaultProductGroupContext.getProductGroup())
			.productDelivery(defaultProductGroupContext.getProductDelivery())
			.productNotice(defaultProductGroupContext.getProductNotice())
			.productDetailDescription(defaultProductGroupContext.getProductDetailDescription())
			.productGroupImages(defaultProductGroupContext.getProductGroupImageContext())
			.products(defaultProductOptionContext)
			.build();
	}

	public List<DefaultProductGroupContext> assemble(List<Long> productGroupIds) {
		List<DefaultProductGroupContext> defaultProductGroupContexts = productGroupFinder.fetchNoProductContextByIds(productGroupIds);
		List<DefaultProductOptionContext> defaultProductOptionContexts = productFinder.fetchByProductGroupIds(
			productGroupIds);
		Map<Long, DefaultProductOptionContext> productGroupIdMap = groupByProductGroupId(defaultProductOptionContexts);

		return defaultProductGroupContexts.stream().map(p -> {
			DefaultProductOptionContext defaultProductOptionContext = productGroupIdMap.get(p.getProductGroupId());
			return DefaultProductGroupContext.builder()
				.productGroup(p.getProductGroup())
				.productDelivery(p.getProductDelivery())
				.productNotice(p.getProductNotice())
				.productDetailDescription(p.getProductDetailDescription())
				.productGroupImages(p.getProductGroupImageContext())
				.products(defaultProductOptionContext)
				.build();
		}).toList();
	}

	private Map<Long, DefaultProductOptionContext> groupByProductGroupId(List<DefaultProductOptionContext> defaultProductOptionContexts){
		return defaultProductOptionContexts.stream()
			.collect(Collectors.toMap(DefaultProductOptionContext::getProductGroupId, Function.identity(), (v1, v2) -> v1));
	}

}
