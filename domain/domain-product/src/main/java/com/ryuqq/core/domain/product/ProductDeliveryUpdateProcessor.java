package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.ProductDeliveryCommand;
import com.ryuqq.core.domain.product.core.UpdateProcessor;

@Component
public class ProductDeliveryUpdateProcessor implements UpdateProcessor<ProductDeliveryCommand> {

	private final ProductDeliveryRegister productDeliveryRegister;

	public ProductDeliveryUpdateProcessor(ProductDeliveryRegister productDeliveryRegister) {
		this.productDeliveryRegister = productDeliveryRegister;
	}

	@Override
	public boolean supports(Class<?> domainType) {
		return ProductDeliveryCommand.class.isAssignableFrom(domainType);
	}

	@Override
	public void processUpdate(ProductDeliveryCommand productDeliveryCommand) {
		productDeliveryRegister.update(productDeliveryCommand);
	}

}
