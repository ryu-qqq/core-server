package com.ryuqq.core.domain.product;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.dao.image.ProductGroupImageCommand;
import com.ryuqq.core.domain.product.dao.image.ProductGroupImagePersistenceRepository;

@Component
public class ProductGroupImageRegister {

	private final ProductGroupImagePersistenceRepository productGroupImagePersistenceRepository;

	public ProductGroupImageRegister(ProductGroupImagePersistenceRepository productGroupImagePersistenceRepository) {
		this.productGroupImagePersistenceRepository = productGroupImagePersistenceRepository;
	}

	public void register(List<? extends ProductGroupImageCommand> productGroupImageCommands) {
		productGroupImagePersistenceRepository.saveAll(productGroupImageCommands);
	}

	public void update(List<? extends ProductGroupImageCommand> productGroupImageCommands){
		productGroupImagePersistenceRepository.updateAll(productGroupImageCommands);
	}

}
