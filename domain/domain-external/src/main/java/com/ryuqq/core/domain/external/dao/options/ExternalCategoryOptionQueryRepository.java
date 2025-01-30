package com.ryuqq.core.domain.external.dao.options;

import java.util.List;

import com.ryuqq.core.domain.external.ExternalCategoryOption;

public interface ExternalCategoryOptionQueryRepository {

	List<ExternalCategoryOption> fetchBySiteIdAndExternalCategoryIds(long siteId, List<String> externalCategoryIds);

}
