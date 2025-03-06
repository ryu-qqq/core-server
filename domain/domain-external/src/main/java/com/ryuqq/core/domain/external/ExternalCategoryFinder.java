package com.ryuqq.core.domain.external;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.core.ExternalCategoryQueryInterface;
import com.ryuqq.core.domain.external.dao.category.ExternalCategoryQueryRepository;


@Component
public class ExternalCategoryFinder implements ExternalCategoryQueryInterface {

	private final ExternalCategoryQueryRepository externalCategoryQueryRepository;

	public ExternalCategoryFinder(ExternalCategoryQueryRepository externalCategoryQueryRepository) {
		this.externalCategoryQueryRepository = externalCategoryQueryRepository;
	}

	public List<DefaultExternalCategoryMapping> fetchBySiteIdAndCategoryIds(long siteId, List<Long> categoryIds){
		return externalCategoryQueryRepository.fetchBySiteIdAndCategoryIds(siteId, categoryIds);
	}

}
