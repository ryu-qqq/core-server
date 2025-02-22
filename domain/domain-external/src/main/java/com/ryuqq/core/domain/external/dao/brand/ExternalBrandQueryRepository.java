package com.ryuqq.core.domain.external.dao.brand;

import java.util.List;

import com.ryuqq.core.domain.external.DefaultExternalBrandMapping;

public interface ExternalBrandQueryRepository {
	DefaultExternalBrandMapping fetchBySiteIdAndBrandId(long siteId, long brandId);
	List<DefaultExternalBrandMapping> fetchBySiteIdAndBrandIds(long siteId, List<Long> brandIds);
}
