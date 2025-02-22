package com.ryuqq.core.domain.external;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ryuqq.core.domain.external.core.SiteRequestProcessorExecutor;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.enums.SyncStatus;

@Service
public class ExternalProductGroupUpdateHandler {

	private final ExternalSiteSellerRelationFinder externalSiteSellerRelationFinder;
	private final ExternalProductGroupFinder externalProductGroupFinder;
	private final SiteRequestProcessorExecutor siteRequestProcessorExecutor;

	public ExternalProductGroupUpdateHandler(ExternalSiteSellerRelationFinder externalSiteSellerRelationFinder,
											 ExternalProductGroupFinder externalProductGroupFinder,
											 SiteRequestProcessorExecutor siteRequestProcessorExecutor) {
		this.externalSiteSellerRelationFinder = externalSiteSellerRelationFinder;
		this.externalProductGroupFinder = externalProductGroupFinder;
		this.siteRequestProcessorExecutor = siteRequestProcessorExecutor;
	}


	public void updateExternalProductGroup(long sellerId, long productGroupId, ProductDomainEventType productDomainEventType) {
		externalSiteSellerRelationFinder.fetchBySellerId(sellerId)
			.ifPresent(relation -> {
				List<Long> siteIds = relation.externalSites()
					.stream()
					.map(ExternalSite::siteId)
					.toList();

				List<ExternalProductGroup> externalProductGroups = externalProductGroupFinder.fetchByProductGroupIdsAndSiteIds(
					productGroupId, siteIds, SyncStatus.getSyncStatus());

				externalProductGroups.forEach(productGroup ->
					siteRequestProcessorExecutor.processRequests(productGroup, productDomainEventType));
		});
	}

}
