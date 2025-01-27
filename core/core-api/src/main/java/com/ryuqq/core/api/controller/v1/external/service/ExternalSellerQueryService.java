package com.ryuqq.core.api.controller.v1.external.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ryuqq.core.domain.external.ExternalSiteSellerRelation;
import com.ryuqq.core.domain.external.ExternalSiteSellerRelationFinder;

@Service
public class ExternalSellerQueryService {

	private final ExternalSiteSellerRelationFinder externalSiteSellerRelationFinder;

	public ExternalSellerQueryService(ExternalSiteSellerRelationFinder externalSiteSellerRelationFinder) {
		this.externalSiteSellerRelationFinder = externalSiteSellerRelationFinder;
	}

	public Optional<ExternalSiteSellerRelation> fetchBySellerId(long sellerId) {
		return externalSiteSellerRelationFinder.fetchBySellerId(sellerId);
	}

	public List<ExternalSiteSellerRelation> fetchExternalSiteSellerRelation() {
		return externalSiteSellerRelationFinder.fetchExternalSiteSellerRelation();
	}

}
