package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.UpdateProcessor;

@Component
public class ProductDeliveryUpdateProcessor implements UpdateProcessor<ProductDelivery> {

	private final ProductDeliveryRegister productDeliveryRegister;

	public ProductDeliveryUpdateProcessor(ProductDeliveryRegister productDeliveryRegister) {
		this.productDeliveryRegister = productDeliveryRegister;
	}

	@Override
	public boolean supports(Class<?> domainType) {
		return ProductDelivery.class.equals(domainType);
	}

	@Override
	public void processUpdate(ProductDelivery entity) {
		productDeliveryRegister.update(entity);
	}

}
