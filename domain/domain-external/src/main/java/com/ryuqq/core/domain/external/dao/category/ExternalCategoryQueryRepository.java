package com.ryuqq.core.domain.external.dao.category;

import java.util.List;

import com.ryuqq.core.domain.external.ExternalCategory;

public interface ExternalCategoryQueryRepository {

	List<ExternalCategory> fetchBySiteIdAndCategoryIds(long siteId, List<Long> categoryIds);


}
