package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.dao.group.ProductGroupCommand;

@Component
public class ProductGroupUpdateProcessor implements UpdateProcessor<ProductGroupCommand> {

	private final ProductGroupRegister productGroupRegister;

	public ProductGroupUpdateProcessor(ProductGroupRegister productGroupRegister) {
		this.productGroupRegister = productGroupRegister;
	}

	@Override
	public boolean supports(Class<?> domainType) {
		return ProductGroupCommand.class.isAssignableFrom(domainType);
	}

	@Override
	public void processUpdate(ProductGroupCommand productGroupCommand) {
		productGroupRegister.update(productGroupCommand);
	}

}
