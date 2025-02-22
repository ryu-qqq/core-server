package com.ryuqq.core.domain.external;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ryuqq.core.enums.SyncStatus;

@Service
public class ExternalProductGroupRegisterHandler {

	private final ExternalSiteSellerRelationFinder externalSiteSellerRelationFinder;
	private final ExternalProductGroupRegister externalProductGroupRegister;
	private final ExternalProductGroupFinder externalProductGroupFinder;

	public ExternalProductGroupRegisterHandler(ExternalSiteSellerRelationFinder externalSiteSellerRelationFinder,
											   ExternalProductGroupRegister externalProductGroupRegister,
											   ExternalProductGroupFinder externalProductGroupFinder) {
		this.externalSiteSellerRelationFinder = externalSiteSellerRelationFinder;
		this.externalProductGroupRegister = externalProductGroupRegister;
		this.externalProductGroupFinder = externalProductGroupFinder;
	}

	public void registerExternalProductGroupWaitingStatus(long sellerId, long productGroupId, long brandId, long categoryId){
		externalSiteSellerRelationFinder.fetchBySellerId(sellerId).ifPresent(e -> {
			List<ExternalProductGroup> externalProductGroups = e.externalSites().stream()
				.filter(site -> !externalProductGroupFinder.existBySiteIdAndProductGroupId(site.siteId(), productGroupId))
				.map(site ->
					ExternalProductGroup.create(site.siteId(), site.siteName(), productGroupId, brandId, categoryId, sellerId, SyncStatus.WAITING)
				).toList();

			externalProductGroupRegister.register(externalProductGroups);
		});
	}
}
