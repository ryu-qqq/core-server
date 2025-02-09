package com.ryuqq.core.domain.product.core;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ryuqq.core.enums.OptionName;
import com.ryuqq.core.enums.OptionType;

@Component
public class ProductOptionValidator implements ProductGroupDomainValidator<ProductOptionContextCommand> {

	@Override
	public boolean supports(Object clazz) {
		return clazz instanceof ProductOptionContextCommand;
	}

	@Override
	public void validate(ProductOptionContextCommand target, ValidationResult result, boolean updated) {

		OptionType optionType = target.optionType();
		List<? extends ProductOptionCommand> productCommands = target.productCommands();

		if (!optionType.isMultiOption() && productCommands.size() > 1) {
			result.addError("Single option type does not allow options.");
		}

		if (productCommands == null || productCommands.isEmpty()) {
			result.addError("product options can not be empty.");
		}

		assert productCommands != null;
		productCommands
			.forEach(p -> validateOption(p.productCommand(), optionType, p.optionContextCommands(), result, updated));



	}

	private void validateOption(ProductCommand productCommand, OptionType optionType, List<? extends OptionContextCommand> options, ValidationResult result, boolean updated) {
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
			checkUpdateField(productCommand.id(), result);
		}
	}

	private void checkUpdateField(Long productId, ValidationResult result) {
		if (productId != null) {
			result.addError("When first enrolling a product, productId must not be included.");
		}
	}

	private Set<OptionName> toOptionNameSet(List<? extends OptionContextCommand> options) {
		return options.stream()
			.map(OptionContextCommand::optionName)
			.collect(Collectors.toSet());
	}

	private boolean isValidTwoStepOptionCombination(Set<OptionName> optionNames) {
		return (optionNames.contains(OptionName.COLOR) && optionNames.contains(OptionName.SIZE)) ||
			(optionNames.contains(OptionName.DEFAULT_ONE) && optionNames.contains(OptionName.DEFAULT_TWO));
	}


}
