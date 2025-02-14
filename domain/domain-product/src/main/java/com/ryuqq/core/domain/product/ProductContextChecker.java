package com.ryuqq.core.domain.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.DefaultProductOptionContext;
import com.ryuqq.core.domain.product.core.Product;
import com.ryuqq.core.domain.product.core.ProductContext;
import com.ryuqq.core.domain.product.core.ProductOptionCommand;
import com.ryuqq.core.domain.product.core.ProductOptionContext;
import com.ryuqq.core.domain.product.core.ProductOptionContextCommand;
import com.ryuqq.core.domain.product.core.UpdateChecker;
import com.ryuqq.core.domain.product.core.UpdateDecision;
import com.ryuqq.core.enums.OptionName;
import com.ryuqq.core.enums.ProductDomainEventType;

@Component
public class ProductContextChecker implements UpdateChecker<ProductOptionContext, ProductOptionContextCommand> {

	private final ProductChecker productChecker;

	public ProductContextChecker(ProductChecker productChecker) {
		this.productChecker = productChecker;
	}

	@Override
	public void checkUpdates(UpdateDecision decision, ProductOptionContext existing, ProductOptionContextCommand updated) {
		List<ProductOptionCommand> changedDefaultProductContext = new ArrayList<>();

		Map<String, ProductContext> existingOptionNameValueMap = existing.toOptionNameValueMap();
		Map<OptionName, Long> optionGroupIdValueMap = existing.toOptionNameMap();
		Map<String, Long> optionDetailMap = existing.toOptionDetailMap();

		for (ProductOptionCommand productOptionCommand : updated.productCommands()) {
			String optionNameValue = productOptionCommand.getOptionNameValue();
			ProductContext existingContext = existingOptionNameValueMap.get(optionNameValue);

			if (existingContext == null) {
				ProductOptionCommand updatedProductOptionCommand =
					ProductOptionContextProcessor.processNewProductContext(productOptionCommand, optionGroupIdValueMap, optionDetailMap);
				changedDefaultProductContext.add(updatedProductOptionCommand);
			} else {
				boolean productUpdated = productChecker.checkUpdates(existingContext.getProduct(), productOptionCommand.productCommand());
				if (productUpdated) {
					Product product = existingContext.getProduct();
					ProductOptionCommand assignedId = productOptionCommand.assignId(product.getId());
					changedDefaultProductContext.add(assignedId);
				}
				existingOptionNameValueMap.remove(optionNameValue);
			}
		}

		ProductOptionContextProcessor.processDeleteProductContext(existingOptionNameValueMap, changedDefaultProductContext);

		if (!changedDefaultProductContext.isEmpty()) {
			ProductOptionContextCommand productOptionContextCommand =
				ProductOptionContextCommand.of(existing.getProductGroupId(), updated.optionType(), changedDefaultProductContext);
			decision.addUpdate(productOptionContextCommand, ProductDomainEventType.STOCK, true);
		}
	}



	@Override
	public boolean supports(Object fieldValue) {
		return fieldValue instanceof DefaultProductOptionContext;
	}

}
