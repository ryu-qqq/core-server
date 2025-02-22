package com.ryuqq.core.storage.db.external;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.external.DefaultExternalBrandMapping;
import com.ryuqq.core.domain.external.dao.brand.ExternalBrandQueryRepository;
import com.ryuqq.core.storage.db.exception.DataNotFoundException;

@Repository
public class DefaultExternalBrandQueryRepository implements ExternalBrandQueryRepository {

	private final ExternalBrandQueryDslRepository externalBrandQueryDslRepository;

	public DefaultExternalBrandQueryRepository(ExternalBrandQueryDslRepository externalBrandQueryDslRepository) {
		this.externalBrandQueryDslRepository = externalBrandQueryDslRepository;
	}

	public DefaultExternalBrandMapping fetchBySiteIdAndBrandId(long siteId, long brandId){
		return externalBrandQueryDslRepository.fetchBySiteIdAndBrandId(siteId, brandId)
			.map(e -> new DefaultExternalBrandMapping(
				e.getSiteId(),
				e.getExternalBrandId(),
				e.getInternalBrandId()
			))
			.orElseThrow(() -> new DataNotFoundException(String.format("External Brand Not Found Site Id: %s, Brand Id: %s", siteId, brandId)));
	}


	@Override
	public List<DefaultExternalBrandMapping> fetchBySiteIdAndBrandIds(long siteId, List<Long> brandIds){
		return externalBrandQueryDslRepository.fetchBySiteIdAndBrandIds(siteId, brandIds).stream()
			.map(e -> new DefaultExternalBrandMapping(
				e.getSiteId(),
				e.getExternalBrandId(),
				e.getInternalBrandId()
			))
			.toList();

	}


}
