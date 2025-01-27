package com.ryuqq.core.external.sellic.mapper;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.category.DefaultCategory;
import com.ryuqq.core.domain.category.core.CategoryQueryInterface;
import com.ryuqq.core.domain.external.core.ExternalCategoryMapping;
import com.ryuqq.core.domain.external.core.ExternalCategoryQueryInterface;
import com.ryuqq.core.external.ExternalSiteException;

@Component
public class SellicCategoryConverter {

	private final ExternalCategoryQueryInterface externalCategoryQueryInterface;
	private final CategoryQueryInterface categoryQueryInterface;

	public SellicCategoryConverter(ExternalCategoryQueryInterface externalCategoryQueryInterface,
								   CategoryQueryInterface categoryQueryInterface) {
		this.externalCategoryQueryInterface = externalCategoryQueryInterface;
		this.categoryQueryInterface = categoryQueryInterface;
	}

	public String fetchExternalCategoryId(long siteId, long categoryId) {
		Optional<String> externalCategoryIdOpt = findExternalCategoryId(siteId, List.of(categoryId));

		if (externalCategoryIdOpt.isPresent()) {
			return externalCategoryIdOpt.get();
		}

		List<Long> parentCategoryIds = fetchParentCategoryIds(categoryId);
		externalCategoryIdOpt = findExternalCategoryId(siteId, parentCategoryIds);

		return externalCategoryIdOpt.orElseThrow(() -> new ExternalSiteException(
			String.format("External Category Not Found Site Id : %s, Category Id : %s", siteId, categoryId)
		));
	}

	private Optional<String> findExternalCategoryId(long siteId, List<Long> categoryIds) {
		List<? extends ExternalCategoryMapping> externalCategoryMappings =
			externalCategoryQueryInterface.fetchBySiteIdAndCategoryIds(siteId, categoryIds);

		return externalCategoryMappings.stream()
			.filter(mapping -> categoryIds.contains(mapping.getCategoryId()))
			.findFirst()
			.map(ExternalCategoryMapping::getExternalCategoryId);
	}

	private List<Long> fetchParentCategoryIds(long categoryId) {
		return categoryQueryInterface.fetchRecursiveByIds(categoryId, true)
			.stream()
			.map(DefaultCategory::getId)
			.toList();
	}
}
