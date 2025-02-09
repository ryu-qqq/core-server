package com.ryuqq.core.domain.product;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.ProductCommand;
import com.ryuqq.core.domain.product.dao.options.ProductPersistenceRepository;

@Component
public class ProductRegister {

	private final ProductPersistenceRepository productPersistenceRepository;

	public ProductRegister(ProductPersistenceRepository productPersistenceRepository) {
		this.productPersistenceRepository = productPersistenceRepository;
	}

	public long register(ProductCommand productCommand) {
		return productPersistenceRepository.save(productCommand);
	}

	public void update(List<ProductCommand> productCommands){
		productPersistenceRepository.updateAll(productCommands);
	}





}
