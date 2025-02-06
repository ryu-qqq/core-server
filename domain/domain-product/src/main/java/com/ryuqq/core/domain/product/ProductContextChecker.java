package com.ryuqq.core.domain.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.UpdateChecker;
import com.ryuqq.core.domain.product.core.UpdateDecision;
import com.ryuqq.core.enums.OptionName;
import com.ryuqq.core.enums.ProductDomainEventType;

@Component
public class ProductContextChecker implements UpdateChecker<ProductContextBundle, ProductContextBundle> {

	private final ProductChecker productChecker;

	public ProductContextChecker(ProductChecker productChecker) {
		this.productChecker = productChecker;
	}

	@Override
	public UpdateDecision checkUpdates(long productGroupId, ProductContextBundle existing, ProductContextBundle updated) {

		UpdateDecision decision = new UpdateDecision();
		List<ProductContext> changedProductContext = new ArrayList<>();

		Map<String, ProductContext> existingMap = toOptionNameValueMap(existing);
		Map<OptionName, Long> optionGroupIdValueMap = toOptionNameMap(existing);
		Map<String, Long> optionDetailMap = toOptionDetailMap(existing);

		for (ProductContext newProductContext : updated.getProducts()) {
			String optionNameValue = newProductContext.getOptionNameValue();
			ProductContext existingContext = existingMap.get(optionNameValue);

			if (existingContext == null) {
				ProductContext updatedProductContext = processNewProductContext(newProductContext, optionGroupIdValueMap, optionDetailMap);
				changedProductContext.add(updatedProductContext);
			}else{
				boolean productUpdated = productChecker.checkUpdates(existingContext.getProduct(), newProductContext.getProduct());
				if (productUpdated) {
					ProductContext updatedProductContext = existingContext.assignProduct(newProductContext.getProduct());
					changedProductContext.add(updatedProductContext);
				}
				existingMap.remove(optionNameValue);
			}
		}

		existingMap.values().forEach(p -> changedProductContext.add(p.deleted()));

		if(!changedProductContext.isEmpty()){
			ProductContextBundle productContextBundle = new ProductContextBundle(changedProductContext);
			ProductContextBundle assignedProductGroupIdBundle = productContextBundle.assignProductGroupId(productGroupId);
			decision.addUpdate(assignedProductGroupIdBundle, ProductDomainEventType.STOCK, true);
		}

		return decision;
	}

	private Map<String, ProductContext> toOptionNameValueMap(ProductContextBundle existing) {
		return existing.getProducts().stream()
			.collect(Collectors.toMap(ProductContext::getOptionNameValue, Function.identity(), (v1, v2) -> v1));
	}


	private Map<OptionName, Long> toOptionNameMap(ProductContextBundle existing) {
		return existing.getProducts().stream()
			.flatMap(productContext -> productContext.getOptions().stream())
			.collect(Collectors.toMap(
				OptionContext::getOptionName,
				OptionContext::getOptionGroupId,
				(existingValue, newValue) -> existingValue
			));
	}

	private Map<String, Long> toOptionDetailMap(ProductContextBundle existing) {
		return existing.getProducts().stream()
			.flatMap(productContext -> productContext.getOptions().stream())
			.collect(Collectors.toMap(
				OptionContext::getOptionValue,
				OptionContext::getOptionDetailId,
				(existingValue, newValue) -> existingValue
			));
	}


	private ProductContext processNewProductContext(ProductContext newProductContext, Map<OptionName, Long> optionGroupIdValueMap, Map<String, Long> optionDetailIdMap) {
		List<OptionContext> updatedOptions = newProductContext.getOptions().stream()
			.map(optionContext -> {
				Long optionGroupId = optionGroupIdValueMap.get(optionContext.getOptionName());
				Long optionDetailId = optionDetailIdMap.get(optionContext.getOptionValue());

				OptionContext updatedOption = optionContext;
				if (optionGroupId != null) {
					updatedOption = updatedOption.assignedOptionGroupId(optionGroupId);
				}
				if (optionDetailId != null) {
					updatedOption = updatedOption.assignedOptionDetailId(optionDetailId);
				}

				return updatedOption;
			})
			.collect(Collectors.toList());

		return newProductContext.assignOptions(updatedOptions);
	}

	@Override
	public boolean supports(Object fieldValue) {
		return fieldValue instanceof ProductContextBundle;
	}

}
