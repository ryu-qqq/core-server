package com.ryuqq.core.domain.product;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.OptionContextCommand;
import com.ryuqq.core.domain.product.core.OptionDetailCommand;
import com.ryuqq.core.enums.OptionName;

@Component
public class OptionDetailHandler {

	private final OptionDetailRegister optionDetailRegister;

	public OptionDetailHandler(OptionDetailRegister optionDetailRegister) {
		this.optionDetailRegister = optionDetailRegister;
	}

	public Map<String, Long> register(List<? extends OptionContextCommand> productOptionCommands, Map<OptionName, Long> optionGroupMap){
		return productOptionCommands.stream()
			.collect(Collectors.toMap(
				OptionContextCommand::optionValue,
				option -> {
					if (option.optionDetailId() > 0) {
						return option.optionDetailId();
					} else {
						Long optionGroupId = optionGroupMap.get(option.optionName());
						OptionDetailCommand optionDetailCommand = OptionDetailCommand.of(optionGroupId,
							option.optionValue());
						return optionDetailRegister.register(optionDetailCommand);
					}
				},
				(existingValue, newValue) -> existingValue
			));
	}

}
