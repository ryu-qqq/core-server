package com.ryuqq.core.domain.external.core;

import java.util.List;

public interface ExternalCategoryOptionQueryInterface {

	List<? extends ExternalCategoryOptionMapping> fetchBySiteIdAndExternalCategoryIds(long siteId, List<String> externalCategoryIds);

}
