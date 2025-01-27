package com.ryuqq.core.domain.product;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ProductDomainHandler {

	private final ProductRegister productRegister;
	private final ProductOptionRegister productOptionRegister;
	private final OptionDomainHandler optionDomainHandler;

	public ProductDomainHandler(ProductRegister productRegister, ProductOptionRegister productOptionRegister, OptionDomainHandler optionDomainHandler) {
		this.productRegister = productRegister;
		this.productOptionRegister = productOptionRegister;
		this.optionDomainHandler = optionDomainHandler;
	}

	public void handleProductDomain(ProductContextBundle productContextBundle) {
		Map<Long, List<OptionContext>> optionMap = new LinkedHashMap<>();

		productContextBundle.getProducts().forEach(productContext -> {
			long productId = productRegister.register(productContext.getProduct());
			if (!productContext.getOptions().isEmpty()) {
				optionMap.put(productId, productContext.getOptions());
			}
		});

		optionDomainHandler.handleOptionMapping(optionMap);
	}

	public void updateProductDomain(ProductContextBundle productContextBundle) {
		List<Long> toDeleteIds = productContextBundle.getProducts()
			.stream()
			.filter(ProductContext::isDeleted)
			.map(ProductContext::getId)
			.toList();

		List<Product> products = productContextBundle.getProducts()
			.stream()
			.map(ProductContext::getProduct)
			.toList();

		productRegister.update(products);

		if(!toDeleteIds.isEmpty()) {
			productOptionRegister.delete(toDeleteIds);
		}


	}




}
