package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.UpdateProcessor;

@Component
public class ProductGroupUpdateProcessor implements UpdateProcessor<ProductGroup> {

	private final ProductGroupRegister productGroupRegister;

	public ProductGroupUpdateProcessor(ProductGroupRegister productGroupRegister) {
		this.productGroupRegister = productGroupRegister;
	}

	@Override
	public boolean supports(Class<?> domainType) {
		return ProductGroup.class.equals(domainType);
	}

	@Override
	public void processUpdate(ProductGroup entity) {
		productGroupRegister.update(entity);
	}
}
