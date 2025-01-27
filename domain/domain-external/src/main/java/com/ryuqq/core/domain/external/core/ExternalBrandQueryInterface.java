package com.ryuqq.core.domain.external.core;

import java.util.List;

public interface ExternalBrandQueryInterface {

	ExternalBrandMapping fetchBySiteIdAndBrandId(long siteId, long brandId);
	List<? extends ExternalBrandMapping> fetchByInternalBrandId(long siteId, List<Long> brandIds);
}
