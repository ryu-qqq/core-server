package com.ryuqq.core.domain.product;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.exception.DomainException;
import com.ryuqq.core.domain.product.core.OptionContextCommand;
import com.ryuqq.core.enums.ErrorType;
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
			.map(option -> {
				Long optionGroupId = optionGroupMap.get(option.optionName());
				Long optionDetailId = optionDetailMap.get(option.optionValue());

				if (optionGroupId == null) {
					throw new DomainException(ErrorType.INVALID_INPUT_ERROR,
						"Option group not found for: " + option.optionName());
				}

				if (optionDetailId == null) {
					throw new DomainException(ErrorType.INVALID_INPUT_ERROR,
						"Option detail not found for: " + option.optionValue());
				}

				return OptionContextCommand.of(
					productId,
					optionGroupId,
					optionDetailId,
					option.optionName(),
					option.optionValue()
				);
			})
			.toList();

		productOptionRegister.register(productOptions);
	}



}
