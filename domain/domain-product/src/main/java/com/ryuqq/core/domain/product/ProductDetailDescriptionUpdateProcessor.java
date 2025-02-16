package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.dao.image.ProductDetailDescriptionCommand;

@Component
public class ProductDetailDescriptionUpdateProcessor implements UpdateProcessor<ProductDetailDescriptionCommand> {

	private final ProductDetailDescriptionRegister productDetailDescriptionRegister;

	public ProductDetailDescriptionUpdateProcessor(ProductDetailDescriptionRegister productDetailDescriptionRegister) {
		this.productDetailDescriptionRegister = productDetailDescriptionRegister;
	}

	@Override
	public boolean supports(Class<?> domainType) {
		return ProductDetailDescriptionCommand.class.isAssignableFrom(domainType);
	}

	@Override
	public void processUpdate(ProductDetailDescriptionCommand productDetailDescriptionCommand) {
		productDetailDescriptionRegister.update(productDetailDescriptionCommand);
	}

}
