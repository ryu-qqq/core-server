package com.ryuqq.core.domain.product;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class ProductGroupContextAssembler {

	private final ProductGroupFinder productGroupFinder;
	private final ProductFinder productFinder;

	public ProductGroupContextAssembler(ProductGroupFinder productGroupFinder, ProductFinder productFinder) {
		this.productGroupFinder = productGroupFinder;
		this.productFinder = productFinder;
	}

	public ProductGroupContext assemble(long productGroupId) {
		ProductGroupContext productGroupContext = productGroupFinder.fetchNoProductContextById(productGroupId);
		ProductContextBundle productContextBundle = productFinder.fetchByProductGroupIds(List.of(productGroupId));
		return ProductGroupContext.builder()
			.productGroup(productGroupContext.getProductGroup())
			.productDelivery(productGroupContext.getProductDelivery())
			.productNotice(productGroupContext.getProductNotice())
			.productDetailDescription(productGroupContext.getProductDetailDescription())
			.productGroupImages(productGroupContext.getProductGroupImages())
			.products(productContextBundle)
			.build();
	}

	public List<ProductGroupContext> assemble(List<Long> productGroupIds) {
		List<ProductGroupContext> productGroupContexts = productGroupFinder.fetchNoProductContextByIds(productGroupIds);
		ProductContextBundle productContextBundle = productFinder.fetchByProductGroupIds(productGroupIds);
		Map<Long, List<ProductContext>> productGroupIdMap = groupByProductGroupId(productContextBundle);
		return productGroupContexts.stream().map(p -> {
			List<ProductContext> productContexts = productGroupIdMap.get(p.getProductGroupId());
			return ProductGroupContext.builder()
				.productGroup(p.getProductGroup())
				.productDelivery(p.getProductDelivery())
				.productNotice(p.getProductNotice())
				.productDetailDescription(p.getProductDetailDescription())
				.productGroupImages(p.getProductGroupImages())
				.products(new ProductContextBundle(productContexts))
				.build();
		}).toList();
	}

	private Map<Long, List<ProductContext>> groupByProductGroupId(ProductContextBundle productContextBundle){
		return productContextBundle.getProducts().stream()
			.collect(Collectors.groupingBy(ProductContext::getProductGroupId));
	}

}
