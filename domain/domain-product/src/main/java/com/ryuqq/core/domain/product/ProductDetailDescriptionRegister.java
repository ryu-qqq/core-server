package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.dao.image.ProductDetailDescriptionCommand;
import com.ryuqq.core.domain.product.dao.image.ProductDetailDescriptionCommandFactory;
import com.ryuqq.core.domain.product.dao.image.ProductDetailDescriptionPersistenceRepository;

@Component
public class ProductDetailDescriptionRegister {

	private final ProductDetailDescriptionPersistenceRepository productDetailDescriptionPersistenceRepository;

	public ProductDetailDescriptionRegister(
		ProductDetailDescriptionPersistenceRepository productDetailDescriptionPersistenceRepository) {
		this.productDetailDescriptionPersistenceRepository = productDetailDescriptionPersistenceRepository;
	}

	public void register(ProductDetailDescription productDetailDescription){
		ProductDetailDescriptionCommand commandFrom = ProductDetailDescriptionCommandFactory.createCommandFrom(
			productDetailDescription);
		productDetailDescriptionPersistenceRepository.save(commandFrom);
	}

	public void update(ProductDetailDescription productDetailDescription){
		ProductDetailDescriptionCommand commandFrom = ProductDetailDescriptionCommandFactory.createCommandFrom(
			productDetailDescription);
		productDetailDescriptionPersistenceRepository.update(commandFrom);
	}

}
