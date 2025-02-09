package com.ryuqq.core.domain.product;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.OptionContextCommand;
import com.ryuqq.core.enums.OptionName;

@Component
public class ProductOptionHandler {

	private final ProductOptionRegister productOptionRegister;

	public ProductOptionHandler(ProductOptionRegister productOptionRegister) {
		this.productOptionRegister = productOptionRegister;
	}


	public void register(Long productId, List<? extends OptionContextCommand> productOptionCommands,
									 Map<OptionName, Long> optionGroupMap,
									 Map<String, Long> optionDetailMap) {
		List<OptionContextCommand> productOptions = productOptionCommands.stream()
			.map(option ->
					OptionContextCommand.of(
						productId,
						optionGroupMap.get(option.optionName()),
						optionDetailMap.get(option.optionValue()),
						option.optionName(),
						option.optionValue()
						))
			.toList();


		productOptionRegister.register(productOptions);
	}


}
