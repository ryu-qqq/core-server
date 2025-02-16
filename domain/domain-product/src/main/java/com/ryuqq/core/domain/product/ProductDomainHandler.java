package com.ryuqq.core.domain.product;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.dao.options.OptionContextCommand;
import com.ryuqq.core.domain.product.dao.options.ProductCommand;
import com.ryuqq.core.domain.product.dao.options.mapping.ProductOptionCommand;
import com.ryuqq.core.domain.product.dao.options.mapping.ProductOptionContextCommand;

@Component
public class ProductDomainHandler {

	private final ProductRegister productRegister;
	private final ProductOptionRegister productOptionRegister;
	private final OptionDomainHandler optionDomainHandler;

	public ProductDomainHandler(ProductRegister productRegister, ProductOptionRegister productOptionRegister, OptionDomainHandler optionDomainHandler) {
		this.productRegister = productRegister;
		this.productOptionRegister = productOptionRegister;
		this.optionDomainHandler = optionDomainHandler;
	}

	public void handle(long productGroupId, ProductOptionContextCommand productOptionContextCommand){
		ProductOptionContextCommand assignProductOptionContextCommand = productOptionContextCommand.assignProductGroupId(productGroupId);

		Map<Long, List<? extends OptionContextCommand>> optionMap = new LinkedHashMap<>();

		assignProductOptionContextCommand.productCommands()
			.forEach(productCommand -> {
				long productId = productRegister.register(productCommand.productCommand());
				if (!productCommand.optionContextCommands().isEmpty()) {
					optionMap.put(productId, productCommand.optionContextCommands());
				}
			});

		optionDomainHandler.handle(optionMap);
	}

	public void handle(ProductOptionContextCommand productOptionContextCommand) {
		List<Long> toDeleteIds = productOptionContextCommand.productCommands()
			.stream()
			.filter(ProductOptionCommand::deleted)
			.map(p -> p.productCommand().id())
			.toList();

		List<ProductCommand> productCommands = productOptionContextCommand.productCommands()
			.stream()
			.map(ProductOptionCommand::productCommand)
			.toList();

		productRegister.update(productCommands);

		if(!toDeleteIds.isEmpty()) {
			productOptionRegister.delete(toDeleteIds);
		}

	}




}
