package com.ryuqq.core.domain.product;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ryuqq.core.domain.product.core.ProductContext;
import com.ryuqq.core.domain.product.dao.options.OptionContextCommand;
import com.ryuqq.core.domain.product.dao.options.ProductCommand;
import com.ryuqq.core.domain.product.dao.options.mapping.ProductOptionCommand;
import com.ryuqq.core.enums.OptionName;

public class ProductOptionContextProcessor {


	public static ProductOptionCommand processNewProductContext(ProductOptionCommand productOptionCommand,
																Map<OptionName, Long> optionGroupIdValueMap,
																Map<String, Long> optionDetailIdMap) {
		List<OptionContextCommand> optionContextCommands = productOptionCommand.optionContextCommands().stream()
			.map(defaultOptionContext -> {
				Long optionGroupId = optionGroupIdValueMap.get(defaultOptionContext.optionName());
				Long optionDetailId = optionDetailIdMap.get(defaultOptionContext.optionValue());

				OptionContextCommand updatedOption = defaultOptionContext;
				if (optionGroupId != null) {
					updatedOption = updatedOption.assignedOptionGroupId(optionGroupId);
				}
				if (optionDetailId != null) {
					updatedOption = updatedOption.assignedOptionDetailId(optionDetailId);
				}

				return updatedOption;
			})
			.collect(Collectors.toList());

		return productOptionCommand.assignProductOptionCommand(optionContextCommands);
	}


	public static void processDeleteProductContext(Map<String, ProductContext> existingOptionNameValueMap,
												   List<ProductOptionCommand> changedDefaultProductContext) {
		existingOptionNameValueMap.values().forEach(p -> {
			ProductCommand productCommand = ProductCommand.of(
				p.getProduct().getId(), p.getProduct().getProductGroupId(), p.getProduct().isSoldOut(),
				p.getProduct().isDisplayed(), p.getProduct().getQuantity(),
				p.getProduct().getAdditionalPrice(), true
			);

			List<OptionContextCommand> optionContextCommands = p.getOptions().stream()
				.map(o -> OptionContextCommand.of(o.getProductId(), o.getOptionGroupId(), o.getOptionDetailId(),
					o.getOptionName(), o.getOptionValue()))
				.toList();

			ProductOptionCommand productOptionCommand = ProductOptionCommand.of(productCommand, true, optionContextCommands);

			changedDefaultProductContext.add(productOptionCommand);
		});
	}

}
