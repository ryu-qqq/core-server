package com.ryuqq.core.api.controller.v1.product.validator;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ryuqq.core.api.controller.v1.product.request.ProductGroupContextCommandRequestDto;
import com.ryuqq.core.api.controller.v1.product.request.ProductInsertRequestDto;
import com.ryuqq.core.api.controller.v1.product.request.ProductOptionInsertRequestDto;
import com.ryuqq.core.enums.OptionName;
import com.ryuqq.core.enums.OptionType;

@Component
public class ProductOptionValidator implements Validator<ProductGroupContextCommandRequestDto> {

	@Override
	public void validate(ProductGroupContextCommandRequestDto target, ValidationResult result, boolean updated) {
		OptionType optionType = target.getOptionType();
		List<ProductInsertRequestDto> productOptions = target.productOptions();


		if (!optionType.isMultiOption() && productOptions.size() > 1) {
			result.addError("Single option type does not allow options.");
		}

		if (productOptions == null || productOptions.isEmpty()) {
			result.addError("product options can not be empty.");
		}

		assert productOptions
			!= null;
		productOptions
			.forEach(p -> validateOption(p.productId(), optionType, p.options(), result, updated));
	}

	private void validateOption(Long productId, OptionType optionType, List<ProductOptionInsertRequestDto> options, ValidationResult result, boolean updated) {
		switch (optionType) {
			case SINGLE -> {
				if (!options.isEmpty()) {
					result.addError("Single option type does not allow options.");
				}
			}
			case OPTION_ONE -> {
				Set<OptionName> optionNames = toOptionNameSet(options);
				if (optionNames.size() != 1 || options.size() > 1) {
					result.addError("One step option type should have only one unique OptionName.");
				}
			}
			case OPTION_TWO -> {
				Set<OptionName> optionNames = toOptionNameSet(options);
				if (optionNames.size() != 2 || !isValidTwoStepOptionCombination(optionNames)) {
					result.addError("Two step options must be a valid combination of Color and Size, or Default_One and Default_Two.");
				}
			}
		}

		if (!updated) {
			checkUpdateField(productId, result);
		}
	}

	private void checkUpdateField(Long productId, ValidationResult result) {
		if (productId != null) {
			result.addError("When first enrolling a product, productId must not be included.");
		}
	}


	private Set<OptionName> toOptionNameSet(List<ProductOptionInsertRequestDto> options) {
		return options.stream()
			.map(ProductOptionInsertRequestDto::optionName)
			.collect(Collectors.toSet());
	}

	private boolean isValidTwoStepOptionCombination(Set<OptionName> optionNames) {
		return (optionNames.contains(OptionName.COLOR) && optionNames.contains(OptionName.SIZE)) ||
			(optionNames.contains(OptionName.DEFAULT_ONE) && optionNames.contains(OptionName.DEFAULT_TWO));
	}

}
