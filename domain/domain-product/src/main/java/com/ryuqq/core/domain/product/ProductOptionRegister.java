package com.ryuqq.core.domain.product;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.OptionContextCommand;
import com.ryuqq.core.domain.product.dao.options.mapping.ProductOptionPersistenceRepository;

@Component
public class ProductOptionRegister {

	private final ProductOptionPersistenceRepository productOptionPersistenceRepository;

	public ProductOptionRegister(ProductOptionPersistenceRepository productOptionPersistenceRepository) {
		this.productOptionPersistenceRepository = productOptionPersistenceRepository;
	}

	public void register(OptionContextCommand optionContextCommand){
		productOptionPersistenceRepository.save(optionContextCommand);
	}

	public void register(List<OptionContextCommand> optionContextCommands){
		productOptionPersistenceRepository.saveAll(optionContextCommands);
	}

	public void update(OptionContextCommand optionContextCommand){
		productOptionPersistenceRepository.update(optionContextCommand);
	}

	public void delete(List<Long> productIds){
		productOptionPersistenceRepository.deleteAll(productIds);
	}

}
