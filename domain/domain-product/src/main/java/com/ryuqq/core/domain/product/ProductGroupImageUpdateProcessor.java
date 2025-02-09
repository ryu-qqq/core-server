package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.ProductGroupImageContextCommand;
import com.ryuqq.core.domain.product.core.UpdateProcessor;

@Component
public class ProductGroupImageUpdateProcessor implements UpdateProcessor<ProductGroupImageContextCommand> {

	private final ProductGroupImageRegister productGroupImageRegister;

	public ProductGroupImageUpdateProcessor(ProductGroupImageRegister productGroupImageRegister) {
		this.productGroupImageRegister = productGroupImageRegister;
	}

	@Override
	public boolean supports(Class<?> domainType) {
		return ProductGroupImageContextCommand.class.isAssignableFrom(domainType);
	}

	@Override
	public void processUpdate(ProductGroupImageContextCommand productGroupImageContextCommand) {
		productGroupImageRegister.update(productGroupImageContextCommand.productGroupImageCommands());
	}

}
