package com.ryuqq.core.domain.external.dao.category;

import java.util.List;

import com.ryuqq.core.domain.external.DefaultExternalCategoryMapping;

public interface ExternalCategoryQueryRepository {

	List<DefaultExternalCategoryMapping> fetchBySiteIdAndCategoryIds(long siteId, List<Long> categoryIds);


}
