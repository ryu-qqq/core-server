package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.UpdateProcessor;

@Component
public class ProductGroupImageUpdateProcessor implements UpdateProcessor<ProductGroupImage> {

	private final ProductGroupImageRegister productGroupImageRegister;

	public ProductGroupImageUpdateProcessor(ProductGroupImageRegister productGroupImageRegister) {
		this.productGroupImageRegister = productGroupImageRegister;
	}

	@Override
	public boolean supports(Class<?> domainType) {
		return ProductGroupImage.class.equals(domainType);
	}

	@Override
	public void processUpdate(ProductGroupImage entity) {
		productGroupImageRegister.update(entity);

	}

}
