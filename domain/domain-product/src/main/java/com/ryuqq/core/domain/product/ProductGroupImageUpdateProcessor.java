package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.UpdateProcessor;

@Component
public class ProductGroupImageUpdateProcessor implements UpdateProcessor<ProductGroupImageBundle> {

	private final ProductGroupImageRegister productGroupImageRegister;

	public ProductGroupImageUpdateProcessor(ProductGroupImageRegister productGroupImageRegister) {
		this.productGroupImageRegister = productGroupImageRegister;
	}

	@Override
	public boolean supports(Class<?> domainType) {
		return ProductGroupImageBundle.class.equals(domainType);
	}

	@Override
	public void processUpdate(ProductGroupImageBundle entity) {
		entity.getImages().forEach(productGroupImageRegister::update);

	}

}
