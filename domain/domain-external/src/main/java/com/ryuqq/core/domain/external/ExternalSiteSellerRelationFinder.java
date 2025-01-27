package com.ryuqq.core.domain.external;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.dao.site.ExternalSiteQueryRepository;

@Component
public class ExternalSiteSellerRelationFinder {

	private final ExternalSiteQueryRepository externalSiteQueryRepository;

	public ExternalSiteSellerRelationFinder(ExternalSiteQueryRepository externalSiteQueryRepository) {
		this.externalSiteQueryRepository = externalSiteQueryRepository;
	}

	public Optional<ExternalSiteSellerRelation> fetchBySellerId(long sellerId) {
		return externalSiteQueryRepository.fetchBySellerId(sellerId);
	}

	public List<ExternalSiteSellerRelation> fetchExternalSiteSellerRelation() {
		return externalSiteQueryRepository.fetchExternalSiteSellerRelation();
	}


}
