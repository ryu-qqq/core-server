package com.ryuqq.core.domain.product;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ryuqq.core.domain.product.core.DefaultProductGroupContext;
import com.ryuqq.core.domain.product.core.ProductGroupContext;
import com.ryuqq.core.domain.product.core.ProductOptionContext;

@Service
public class ProductContextAssembler {

	private static final Logger logger = LoggerFactory.getLogger(ProductContextAssembler.class);

	private final ProductFinder productFinder;

	public ProductContextAssembler(ProductFinder productFinder) {
		this.productFinder = productFinder;
	}

	public ProductGroupContext assembleWithProducts(ProductGroupContext productGroupContext) {
		ProductOptionContext productOptionContext = productFinder.fetchByProductGroupId(productGroupContext.getId());
		return build(productGroupContext, productOptionContext);
	}

	public List<ProductGroupContext> assembleWithProducts(List<? extends ProductGroupContext> groupContexts) {
		List<Long> productGroupIds = groupContexts.stream()
			.map(ProductGroupContext::getId)
			.toList();

		if(productGroupIds.isEmpty()) return List.of();

		Map<Long, ProductOptionContext> productOptionsMap = mapOptionsByProductGroupId(
			productFinder.fetchByProductGroupIds(productGroupIds)
		);

		return groupContexts.stream()
			.map(p -> build(p, productOptionsMap.get(p.getId())))
			.toList();
	}


	private Map<Long, ProductOptionContext> mapOptionsByProductGroupId(List<? extends ProductOptionContext> productOptions) {
		return productOptions.stream()
			.collect(Collectors.toMap(ProductOptionContext::getProductGroupId, Function.identity(), (existing, duplicate) -> {
				logger.warn("Detected Duplicated ProductGroupId: " + existing.getProductGroupId());
				return existing;
			}));
	}

	private ProductGroupContext build(ProductGroupContext productGroupContext, ProductOptionContext productOptionContext) {
		return DefaultProductGroupContext.builder()
			.productGroup(productGroupContext.getProductGroup())
			.productDelivery(productGroupContext.getProductDelivery())
			.productNotice(productGroupContext.getProductNotice())
			.productDetailDescription(productGroupContext.getProductDetailDescription())
			.productGroupImages(productGroupContext.getProductGroupImageContext())
			.products(productOptionContext)
			.build();
	}

}
