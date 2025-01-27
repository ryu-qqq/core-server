package com.ryuqq.core.domain.external.core;

import java.util.List;

public interface ExternalCategoryQueryInterface {

	List<? extends ExternalCategoryMapping> fetchBySiteIdAndCategoryIds(long siteId, List<Long> categoryIds);
}
