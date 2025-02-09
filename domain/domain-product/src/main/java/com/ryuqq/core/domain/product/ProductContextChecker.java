package com.ryuqq.core.domain.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.OptionContext;
import com.ryuqq.core.domain.product.core.OptionContextCommand;
import com.ryuqq.core.domain.product.core.Product;
import com.ryuqq.core.domain.product.core.ProductCommand;
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

		Map<String, ProductContext> existingOptionNameValueMap = toOptionNameValueMap(existing);
		Map<OptionName, Long> optionGroupIdValueMap = toOptionNameMap(existing);
		Map<String, Long> optionDetailMap = toOptionDetailMap(existing);

		for (ProductOptionCommand productOptionCommand : updated.productCommands()) {
			String optionNameValue = productOptionCommand.getOptionNameValue();
			ProductContext existingContext = existingOptionNameValueMap.get(optionNameValue);

			if (existingContext == null) {
				ProductOptionCommand updatedProductOptionCommand = processNewProductContext(productOptionCommand, optionGroupIdValueMap, optionDetailMap);
				changedDefaultProductContext.add(updatedProductOptionCommand);
			}else{
				boolean productUpdated = productChecker.checkUpdates(existingContext.getProduct(), productOptionCommand.productCommand());
				if (productUpdated) {
					Product product = existingContext.getProduct();

					ProductOptionCommand assignedId = productOptionCommand.assignId(product.getId());
					changedDefaultProductContext.add(assignedId);
				}
				existingOptionNameValueMap.remove(optionNameValue);
			}
		}

		processDeleteProductContext(existingOptionNameValueMap, changedDefaultProductContext);

		if(!changedDefaultProductContext.isEmpty()){
			ProductOptionContextCommand productOptionContextCommand = ProductOptionContextCommand.of(existing.getProductGroupId(), updated.optionType(), changedDefaultProductContext);
			decision.addUpdate(productOptionContextCommand, ProductDomainEventType.STOCK, true);
		}

	}

	private Map<String, ProductContext> toOptionNameValueMap(ProductOptionContext existing) {
		return existing.getProducts().stream()
			.collect(Collectors.toMap(ProductContext::getOptionNameValue, Function.identity(), (v1, v2) -> v1));
	}


	private Map<OptionName, Long> toOptionNameMap(ProductOptionContext existing) {
		return existing.getProducts().stream()
			.flatMap(productContext -> productContext.getOptions().stream())
			.collect(Collectors.toMap(
				OptionContext::getOptionName,
				OptionContext::getOptionGroupId,
				(existingValue, newValue) -> existingValue
			));
	}

	private Map<String, Long> toOptionDetailMap(ProductOptionContext existing) {
		return existing.getProducts().stream()
			.flatMap(productContext -> productContext.getOptions().stream())
			.collect(Collectors.toMap(
				OptionContext::getOptionValue,
				OptionContext::getOptionDetailId,
				(existingValue, newValue) -> existingValue
			));
	}


	private ProductOptionCommand processNewProductContext(ProductOptionCommand productOptionCommand, Map<OptionName, Long> optionGroupIdValueMap, Map<String, Long> optionDetailIdMap) {
		List<OptionContextCommand> optionContextCommands = productOptionCommand.optionContextCommands().stream()
			.map(defaultOptionContext -> {
				Long optionGroupId = optionGroupIdValueMap.get(defaultOptionContext.optionName());
				Long optionDetailId = optionDetailIdMap.get(defaultOptionContext.optionValue());

				OptionContextCommand updatedOption = defaultOptionContext;
				if (optionGroupId
					!= null) {
					updatedOption = updatedOption.assignedOptionGroupId(optionGroupId);
				}
				if (optionDetailId
					!= null) {
					updatedOption = updatedOption.assignedOptionDetailId(optionDetailId);
				}

				return updatedOption;
			})
			.collect(Collectors.toList());


		return productOptionCommand.assignProductOptionCommand(optionContextCommands);
	}

	private void processDeleteProductContext(Map<String, ProductContext> existingOptionNameValueMap, List<ProductOptionCommand> changedDefaultProductContext) {
		existingOptionNameValueMap.values().forEach(p -> {
			Product product = p.getProduct();
			List<? extends OptionContext> options = p.getOptions();

			ProductCommand productCommand = ProductCommand.of(
				product.getId(), product.getProductGroupId(), product.isSoldOut(),
				product.isDisplayed(), product.getQuantity(),
				product.getAdditionalPrice(), true
			);

			List<OptionContextCommand> optionContextCommands = options.stream()
				.map(o -> OptionContextCommand.of(o.getProductId(), o.getOptionGroupId(), o.getOptionDetailId(),
					o.getOptionName(), o.getOptionValue()))
				.toList();

			ProductOptionCommand productOptionCommand = ProductOptionCommand.of(productCommand, true, optionContextCommands);

			changedDefaultProductContext.add(productOptionCommand);
		});
	}



	@Override
	public boolean supports(Object fieldValue) {
		return fieldValue instanceof DefaultProductOptionContext;
	}

}
