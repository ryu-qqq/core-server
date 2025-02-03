package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.dao.delivery.ProductDeliveryCommandFactory;
import com.ryuqq.core.domain.product.dao.delivery.ProductDeliveryPersistenceRepository;

@Component
public class ProductDeliveryRegister {

	private final ProductDeliveryPersistenceRepository productDeliveryPersistenceRepository;

	public ProductDeliveryRegister(ProductDeliveryPersistenceRepository productDeliveryPersistenceRepository) {
		this.productDeliveryPersistenceRepository = productDeliveryPersistenceRepository;
	}

	public void register(ProductDelivery productDelivery){
		productDeliveryPersistenceRepository.save(ProductDeliveryCommandFactory.createCommandFrom(productDelivery));
	}

	public void update(ProductDelivery productDelivery){
		productDeliveryPersistenceRepository.update(ProductDeliveryCommandFactory.createCommandFrom(productDelivery));
	}


}
