package com.ryuqq.core.domain.product;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.dao.options.OptionContextCommand;
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

	public void handle(Map<Long, List<? extends OptionContextCommand>> productOptionCommandMap){
		if (productOptionCommandMap == null || productOptionCommandMap.isEmpty()) {
			return;
		}

		productOptionCommandMap.forEach((productId, options) -> {
			Map<OptionName, Long> optionGroupMap = optionGroupHandler.register(options);
			Map<String, Long> optionDetailMap = optionDetailHandler.register(options, optionGroupMap);
			productOptionHandler.register(productId, options, optionGroupMap, optionDetailMap);
		});
	}


}
