package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.UpdateProcessor;

@Component
public class ProductDetailDescriptionUpdateProcessor implements UpdateProcessor<ProductDetailDescription> {

	private final ProductDetailDescriptionRegister productDetailDescriptionRegister;

	public ProductDetailDescriptionUpdateProcessor(ProductDetailDescriptionRegister productDetailDescriptionRegister) {
		this.productDetailDescriptionRegister = productDetailDescriptionRegister;
	}

	@Override
	public boolean supports(Class<?> domainType) {
		return ProductDetailDescription.class.equals(domainType);
	}

	@Override
	public void processUpdate(ProductDetailDescription entity) {
		productDetailDescriptionRegister.update(entity);
	}

}
