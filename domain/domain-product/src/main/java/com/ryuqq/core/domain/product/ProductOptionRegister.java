package com.ryuqq.core.domain.product;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.dao.options.mapping.ProductOptionCommand;
import com.ryuqq.core.domain.product.dao.options.mapping.ProductOptionPersistenceRepository;

@Component
public class ProductOptionRegister {

	private final ProductOptionPersistenceRepository productOptionPersistenceRepository;

	public ProductOptionRegister(ProductOptionPersistenceRepository productOptionPersistenceRepository) {
		this.productOptionPersistenceRepository = productOptionPersistenceRepository;
	}

	public void register(ProductOption productOption){
		productOptionPersistenceRepository.save(productOption.toCommand());
	}

	public void register(List<ProductOption> productOptions){
		List<ProductOptionCommand> productOptionCommands = productOptions.stream()
			.map(ProductOption::toCommand)
			.toList();

		productOptionPersistenceRepository.saveAll(productOptionCommands);
	}

	public void update(ProductOption productOption){
		productOptionPersistenceRepository.update(productOption.toCommand());
	}

	public void delete(List<Long> productIds){
		productOptionPersistenceRepository.deleteAll(productIds);
	}

}
