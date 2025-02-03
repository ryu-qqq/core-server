package com.ryuqq.core.external.buyma.helper;

import java.util.List;

import com.ryuqq.core.domain.external.core.ExternalCategoryOptionMapping;

public class BuyMaSizeMatcher {

	public static long findMatchingOptionId(String size, List<? extends ExternalCategoryOptionMapping> externalCategoryOptionMappings) {
		return externalCategoryOptionMappings.stream()
			.filter(option -> size.equals(option.getOptionValue()))
			.map(ExternalCategoryOptionMapping::getOptionGroupId)
			.findFirst()
			.orElse(0L);
	}

}
