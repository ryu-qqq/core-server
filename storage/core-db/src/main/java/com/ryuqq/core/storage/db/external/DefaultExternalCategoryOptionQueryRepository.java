package com.ryuqq.core.storage.db.external;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.external.DefaultExternalCategoryOptionMapping;
import com.ryuqq.core.domain.external.dao.options.ExternalCategoryOptionQueryRepository;

@Repository
public class DefaultExternalCategoryOptionQueryRepository implements ExternalCategoryOptionQueryRepository {

	private final ExternalCategoryOptionQueryDslRepository externalCategoryOptionQueryDslRepository;

	public DefaultExternalCategoryOptionQueryRepository(
		ExternalCategoryOptionQueryDslRepository externalCategoryOptionQueryDslRepository) {
		this.externalCategoryOptionQueryDslRepository = externalCategoryOptionQueryDslRepository;
	}

	@Override
	public List<DefaultExternalCategoryOptionMapping> fetchBySiteIdAndExternalCategoryIds(long siteId, List<String> externalCategoryIds) {
		return externalCategoryOptionQueryDslRepository.fetchBySiteIdAndCategoryIds(siteId, externalCategoryIds).stream()
			.map(e -> new DefaultExternalCategoryOptionMapping(
				e.getSiteId(),
				e.getExternalCategoryId(),
				e.getOptionGroupId(),
				e.getOptionId(),
				e.getOptionValue()
			))
			.toList();
	}
}
