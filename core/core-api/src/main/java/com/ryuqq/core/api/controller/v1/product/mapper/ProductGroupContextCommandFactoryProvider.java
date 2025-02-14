package com.ryuqq.core.api.controller.v1.product.mapper;

import org.springframework.stereotype.Component;

@Component
public class ProductGroupContextCommandFactoryProvider {

	private final CreateProductGroupContextCommandFactory createProductGroupContextCommandFactory;

	public ProductGroupContextCommandFactoryProvider(CreateProductGroupContextCommandFactory createProductGroupContextCommandFactory) {
		this.createProductGroupContextCommandFactory = createProductGroupContextCommandFactory;
	}

	public ProductGroupContextCommandFactory getProvider(){
		return createProductGroupContextCommandFactory;
	}

}
