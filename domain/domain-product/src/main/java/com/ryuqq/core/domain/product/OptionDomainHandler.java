package com.ryuqq.core.domain.product;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ryuqq.core.enums.OptionName;

@Component
public class OptionDomainHandler {

	private final OptionGroupHandler optionGroupHandler;
	private final OptionDetailHandler optionDetailHandler;
	private final ProductOptionHandler productOptionHandler;

	public OptionDomainHandler(OptionGroupHandler optionGroupHandler,
							   OptionDetailHandler optionDetailHandler,
							   ProductOptionHandler productOptionHandler) {
		this.optionGroupHandler = optionGroupHandler;
		this.optionDetailHandler = optionDetailHandler;
		this.productOptionHandler = productOptionHandler;
	}

	public void handleOptionMapping(Map<Long, List<OptionContext>> productOptionMap) {
		if (productOptionMap == null || productOptionMap.isEmpty()) {
			return;
		}

		productOptionMap.forEach((productId, options) -> {
			Map<OptionName, Long> optionGroupMap = optionGroupHandler.registerOptionGroups(options);
			Map<String, Long> optionDetailMap = optionDetailHandler.registerOptionDetails(options, optionGroupMap);
			productOptionHandler.createProductOptions(productId, options, optionGroupMap, optionDetailMap);
		});
	}

}
