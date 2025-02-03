package com.ryuqq.core.domain.external.dao.options;

import java.util.List;

import com.ryuqq.core.domain.external.ExternalProduct;

public interface ExternalProductQueryRepository {
	List<ExternalProduct> fetchByExternalProductGroupId(String externalProductGroupId);
	List<ExternalProduct> fetchByExternalProductGroupIds(List<String> externalProductGroupIds);

}
