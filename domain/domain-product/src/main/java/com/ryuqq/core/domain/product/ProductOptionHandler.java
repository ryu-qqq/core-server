package com.ryuqq.core.domain.product;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ryuqq.core.enums.OptionName;

@Component
public class ProductOptionHandler {

	private final ProductOptionRegister productOptionRegister;

	public ProductOptionHandler(ProductOptionRegister productOptionRegister) {
		this.productOptionRegister = productOptionRegister;
	}

	public void createProductOptions(Long productId, List<OptionContext> options,
													Map<OptionName, Long> optionGroupMap,
													Map<String, Long> optionDetailMap) {
		List<ProductOption> productOptions = options.stream()
			.map(option -> ProductOption.create(
				productId,
				optionGroupMap.get(option.getOptionName()),
				optionDetailMap.get(option.getOptionValue())
			))
			.toList();

		register(productOptions);
	}

	private void register(List<ProductOption> productOptions) {
		productOptionRegister.register(productOptions);
	}
}
