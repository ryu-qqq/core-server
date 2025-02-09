package com.ryuqq.core.domain.product;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.ProductGroupCommand;
import com.ryuqq.core.domain.product.dao.group.ProductGroupPersistenceRepository;

@Component
public class ProductGroupRegister {

	private final ProductGroupPersistenceRepository productGroupPersistenceRepository;

	public ProductGroupRegister(ProductGroupPersistenceRepository productGroupPersistenceRepository) {
		this.productGroupPersistenceRepository = productGroupPersistenceRepository;
	}

	public long register(ProductGroupCommand productGroupCommand) {
		return productGroupPersistenceRepository.save(productGroupCommand);
	}

	public void update(ProductGroupCommand productGroupCommand) {
		productGroupPersistenceRepository.update(productGroupCommand);
	}

}
