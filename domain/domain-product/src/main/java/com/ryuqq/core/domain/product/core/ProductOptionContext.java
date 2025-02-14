package com.ryuqq.core.domain.product.core;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.ryuqq.core.enums.OptionName;
import com.ryuqq.core.enums.OptionType;

public interface ProductOptionContext {
	long getProductGroupId();
	OptionType getOptionType();
	List<? extends ProductContext> getProducts();


	default Map<String, ProductContext> toOptionNameValueMap() {
		return getProducts().stream()
			.collect(Collectors.toMap(ProductContext::getOptionNameValue, Function.identity(), (v1, v2) -> v1));
	}


	default Map<OptionName, Long> toOptionNameMap() {
		return getProducts().stream()
			.flatMap(productContext -> productContext.getOptions().stream())
			.collect(Collectors.toMap(
				OptionContext::getOptionName,
				OptionContext::getOptionGroupId,
				(existingValue, newValue) -> existingValue
			));
	}

	default Map<String, Long> toOptionDetailMap() {
		return getProducts().stream()
			.flatMap(productContext -> productContext.getOptions().stream())
			.collect(Collectors.toMap(
				OptionContext::getOptionValue,
				OptionContext::getOptionDetailId,
				(existingValue, newValue) -> existingValue
			));
	}


}
