package com.ryuqq.core.domain.external.dao.options;

import java.util.List;

import com.ryuqq.core.domain.external.DefaultExternalCategoryOptionMapping;

public interface ExternalCategoryOptionQueryRepository {

	List<DefaultExternalCategoryOptionMapping> fetchBySiteIdAndExternalCategoryIds(long siteId, List<String> externalCategoryIds);

}
