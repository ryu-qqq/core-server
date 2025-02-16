package com.ryuqq.core.domain.product;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.dao.options.OptionContextCommand;
import com.ryuqq.core.domain.product.dao.options.group.OptionGroupCommand;
import com.ryuqq.core.enums.OptionName;

@Component
public class OptionGroupHandler {

	private final OptionGroupRegister optionGroupRegister;

	public OptionGroupHandler(OptionGroupRegister optionGroupRegister) {
		this.optionGroupRegister = optionGroupRegister;
	}

	public Map<OptionName, Long> register(List<? extends OptionContextCommand> productOptionCommands){
		return productOptionCommands.stream()
			.collect(Collectors.toMap(
				OptionContextCommand::optionName,
				option -> {
					if (option.optionGroupId() >0) {
						return option.optionGroupId();
					} else {
						return optionGroupRegister.register(OptionGroupCommand.of(option.optionName()));
					}
				},
				(existingValue, newValue) -> existingValue
			));
	}
}
