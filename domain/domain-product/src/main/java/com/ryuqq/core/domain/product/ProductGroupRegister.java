package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.dao.group.ProductGroupCommandFactory;
import com.ryuqq.core.domain.product.dao.group.ProductGroupPersistenceRepository;

@Component
public class ProductGroupRegister {

	private final ProductGroupPersistenceRepository productGroupPersistenceRepository;

	public ProductGroupRegister(ProductGroupPersistenceRepository productGroupPersistenceRepository) {
		this.productGroupPersistenceRepository = productGroupPersistenceRepository;
	}

	public long register(ProductGroup productGroup) {
		return productGroupPersistenceRepository.save(ProductGroupCommandFactory.createCommandFrom(productGroup));
	}

	public void update(ProductGroup productGroup) {
		productGroupPersistenceRepository.save(ProductGroupCommandFactory.createCommandFrom(productGroup));
	}

}
