package com.ryuqq.core.api.controller.v1.product.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ProductGroupContextCommandFactoryProvider {

	private final CreateProductGroupContextCommandFactory createProductGroupContextCommandFactory;
	private final UpdateProductGroupContextCommandFactory updateProductGroupContextCommandFactory;

	public ProductGroupContextCommandFactoryProvider(List<ProductGroupContextCommandFactory> factories,
													 CreateProductGroupContextCommandFactory createProductGroupContextCommandFactory,
													 UpdateProductGroupContextCommandFactory updateProductGroupContextCommandFactory) {
		this.createProductGroupContextCommandFactory = createProductGroupContextCommandFactory;
		this.updateProductGroupContextCommandFactory = updateProductGroupContextCommandFactory;
	}

	public ProductGroupContextCommandFactory getProvider(boolean isUpdate){
		if(isUpdate){
			return updateProductGroupContextCommandFactory;
		}
		return createProductGroupContextCommandFactory;
	}

}
