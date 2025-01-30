package com.ryuqq.core.domain.external;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.core.ExternalCategoryOptionMapping;
import com.ryuqq.core.domain.external.core.ExternalCategoryOptionQueryInterface;
import com.ryuqq.core.domain.external.dao.options.ExternalCategoryOptionQueryRepository;

@Component
public class ExternalCategoryOptionFinder implements ExternalCategoryOptionQueryInterface {

	private final ExternalCategoryOptionQueryRepository externalCategoryOptionQueryRepository;

	public ExternalCategoryOptionFinder(ExternalCategoryOptionQueryRepository externalCategoryOptionQueryRepository) {
		this.externalCategoryOptionQueryRepository = externalCategoryOptionQueryRepository;
	}

	@Override
	public List<? extends ExternalCategoryOptionMapping> fetchBySiteIdAndExternalCategoryIds(long siteId,
																							 List<String> externalCategoryIds) {
		return externalCategoryOptionQueryRepository.fetchBySiteIdAndExternalCategoryIds(siteId, externalCategoryIds);
	}
}
