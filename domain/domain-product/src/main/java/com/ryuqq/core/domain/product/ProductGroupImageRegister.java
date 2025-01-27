package com.ryuqq.core.domain.product;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.dao.image.ProductGroupImageCommand;
import com.ryuqq.core.domain.product.dao.image.ProductGroupImageCommandFactory;
import com.ryuqq.core.domain.product.dao.image.ProductGroupImagePersistenceRepository;

@Component
public class ProductGroupImageRegister {

	private final ProductGroupImagePersistenceRepository productGroupImagePersistenceRepository;

	public ProductGroupImageRegister(ProductGroupImagePersistenceRepository productGroupImagePersistenceRepository) {
		this.productGroupImagePersistenceRepository = productGroupImagePersistenceRepository;
	}

	public void register(ProductGroupImageBundle productGroupImageBundle) {
		List<ProductGroupImageCommand> productGroupImageCommands = productGroupImageBundle.getImages().stream()
			.map(ProductGroupImageCommandFactory::createCommandFrom)
			.toList();
		productGroupImagePersistenceRepository.saveAll(productGroupImageCommands);
	}

	public void update(ProductGroupImage productGroupImage){
		ProductGroupImageCommand commandFrom = ProductGroupImageCommandFactory.createCommandFrom(productGroupImage);
		productGroupImagePersistenceRepository.update(commandFrom);
	}

}
