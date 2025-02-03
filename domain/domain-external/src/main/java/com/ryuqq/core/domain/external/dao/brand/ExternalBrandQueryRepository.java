package com.ryuqq.core.domain.external.dao.brand;

import java.util.List;

import com.ryuqq.core.domain.external.ExternalBrand;

public interface ExternalBrandQueryRepository {
	ExternalBrand fetchBySiteIdAndBrandId(long siteId, long brandId);
	List<ExternalBrand> fetchBySiteIdAndBrandIds(long siteId, List<Long> brandIds);
}
