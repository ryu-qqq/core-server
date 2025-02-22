package com.ryuqq.core.domain.external;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.core.ExternalBrandQueryInterface;
import com.ryuqq.core.domain.external.dao.brand.ExternalBrandQueryRepository;

@Component
public class ExternalBrandFinder implements ExternalBrandQueryInterface {

	private final ExternalBrandQueryRepository externalBrandQueryRepository;

	public ExternalBrandFinder(ExternalBrandQueryRepository externalBrandQueryRepository) {
		this.externalBrandQueryRepository = externalBrandQueryRepository;
	}

	public DefaultExternalBrandMapping fetchBySiteIdAndBrandId(long siteId, long brandId){
		return externalBrandQueryRepository.fetchBySiteIdAndBrandId(siteId, brandId);
	}


	public List<DefaultExternalBrandMapping> fetchByInternalBrandId(long siteId, List<Long> brandIds){
		return externalBrandQueryRepository.fetchBySiteIdAndBrandIds(siteId, brandIds);
	}


}
