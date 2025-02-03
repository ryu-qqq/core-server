package com.ryuqq.core.domain.product;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ryuqq.core.enums.OptionName;

@Component
public class OptionGroupHandler {

	private final OptionGroupRegister optionGroupRegister;

	public OptionGroupHandler(OptionGroupRegister optionGroupRegister) {
		this.optionGroupRegister = optionGroupRegister;
	}

	public Map<OptionName, Long> registerOptionGroups(List<OptionContext> options) {
		return options.stream()
			.collect(Collectors.toMap(
				OptionContext::getOptionName,
				option -> {
					if (option.getOptionGroupId() != null) {
						return option.getOptionGroupId();
					} else {
						return optionGroupRegister.register(OptionGroup.create(option.getOptionName()));
					}
				},
				(existingValue, newValue) -> existingValue
			));
	}
}
