package com.ryuqq.core.storage.db.external;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.external.ExternalCategory;
import com.ryuqq.core.domain.external.dao.category.ExternalCategoryQueryRepository;

@Repository
public class DefaultExternalCategoryQueryRepository implements ExternalCategoryQueryRepository {

	private final ExternalCategoryQueryDslRepository externalCategoryQueryDslRepository;

	public DefaultExternalCategoryQueryRepository(ExternalCategoryQueryDslRepository externalCategoryQueryDslRepository) {
		this.externalCategoryQueryDslRepository = externalCategoryQueryDslRepository;
	}

	@Override
	public List<ExternalCategory> fetchBySiteIdAndCategoryIds(long siteId, List<Long> categoryIds){
		return externalCategoryQueryDslRepository.fetchBySiteIdAndCategoryIds(siteId, categoryIds).stream()
			.map(e -> new ExternalCategory(
				e.getSiteId(),
				e.getExternalCategoryId(),
				e.getExternalExtraCategoryId(),
				e.getDescription(),
				e.getInternalCategoryId()
			))
			.toList();
	}


}
