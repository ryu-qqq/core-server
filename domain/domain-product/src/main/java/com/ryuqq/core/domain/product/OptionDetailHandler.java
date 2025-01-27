package com.ryuqq.core.domain.product;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ryuqq.core.enums.OptionName;

@Component
public class OptionDetailHandler {

	private final OptionDetailRegister optionDetailRegister;

	public OptionDetailHandler(OptionDetailRegister optionDetailRegister) {
		this.optionDetailRegister = optionDetailRegister;
	}

	public Map<String, Long> registerOptionDetails(List<OptionContext> options, Map<OptionName, Long> optionGroupMap) {
		return options.stream()
			.collect(Collectors.toMap(
				OptionContext::getOptionValue,
				option -> {
					if (option.getOptionDetailId() != null) {
						return option.getOptionDetailId();
					} else {
						Long optionGroupId = optionGroupMap.get(option.getOptionName());
						OptionDetail newOptionDetail = OptionDetail.create(optionGroupId, option.getOptionValue());
						return optionDetailRegister.register(newOptionDetail);
					}
				},
				(existingValue, newValue) -> existingValue
			));
	}



}
