package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.dao.delivery.ProductDeliveryCommand;
import com.ryuqq.core.domain.product.dao.delivery.ProductDeliveryPersistenceRepository;

@Component
public class ProductDeliveryRegister {

	private final ProductDeliveryPersistenceRepository productDeliveryPersistenceRepository;

	public ProductDeliveryRegister(ProductDeliveryPersistenceRepository productDeliveryPersistenceRepository) {
		this.productDeliveryPersistenceRepository = productDeliveryPersistenceRepository;
	}

	public void register(ProductDeliveryCommand productDeliveryCommand){
		productDeliveryPersistenceRepository.save(productDeliveryCommand);
	}

	public void update(ProductDeliveryCommand productDeliveryCommand){
		productDeliveryPersistenceRepository.update(productDeliveryCommand);
	}


}
