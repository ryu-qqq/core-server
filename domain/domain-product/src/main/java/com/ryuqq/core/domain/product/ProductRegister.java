package com.ryuqq.core.domain.product;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.dao.options.ProductCommand;
import com.ryuqq.core.domain.product.dao.options.ProductPersistenceRepository;

@Component
public class ProductRegister {

	private final ProductPersistenceRepository productPersistenceRepository;

	public ProductRegister(ProductPersistenceRepository productPersistenceRepository) {
		this.productPersistenceRepository = productPersistenceRepository;
	}

	public long register(Product product) {
		return productPersistenceRepository.save(product.toCommand());
	}

	public void update(List<Product> products){
		List<ProductCommand> productCommands = products.stream()
			.map(Product::toCommand)
			.toList();

		productPersistenceRepository.updateAll(productCommands);
	}





}
