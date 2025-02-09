package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.ProductDetailDescriptionCommand;
import com.ryuqq.core.domain.product.dao.image.ProductDetailDescriptionPersistenceRepository;

@Component
public class ProductDetailDescriptionRegister {

	private final ProductDetailDescriptionPersistenceRepository productDetailDescriptionPersistenceRepository;

	public ProductDetailDescriptionRegister(
		ProductDetailDescriptionPersistenceRepository productDetailDescriptionPersistenceRepository) {
		this.productDetailDescriptionPersistenceRepository = productDetailDescriptionPersistenceRepository;
	}

	public void register(ProductDetailDescriptionCommand productDetailDescriptionCommand){
		productDetailDescriptionPersistenceRepository.save(productDetailDescriptionCommand);
	}

	public void update(ProductDetailDescriptionCommand productDetailDescriptionCommand){
		productDetailDescriptionPersistenceRepository.update(productDetailDescriptionCommand);
	}

}
