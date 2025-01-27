package com.ryuqq.core.external;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.ExternalCategory;
import com.ryuqq.core.domain.external.ExternalCategoryFinder;

@Component
public class ExternalCategoryMappingHandler {

	private final ExternalCategoryFinder externalCategoryFinder;

	public ExternalCategoryMappingHandler(ExternalCategoryFinder externalCategoryFinder) {
		this.externalCategoryFinder = externalCategoryFinder;
	}

	public String getExternalCategoryId(long siteId, List<Long> categoryIds) {

		List<ExternalCategory> externalCategories = externalCategoryFinder.fetchBySiteIdAndCategoryIds(
			siteId, categoryIds);
		return externalCategories.stream()
			.findFirst()
			.map(ExternalCategory::externalCategoryId)
			.orElseThrow(() -> new ExternalSiteException(
				String.format("External Category Not Found Site Id : %s, Category Id : %s", siteId, categoryIds))
			);
	}
}
