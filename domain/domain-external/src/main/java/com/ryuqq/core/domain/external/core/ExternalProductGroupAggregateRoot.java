package com.ryuqq.core.domain.external.core;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.external.ExternalProductGroupFinder;
import com.ryuqq.core.domain.external.ExternalProductGroupRegister;
import com.ryuqq.core.domain.external.ExternalSite;
import com.ryuqq.core.domain.external.ExternalSiteSellerRelationFinder;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.enums.SyncStatus;

@Service
public class ExternalProductGroupAggregateRoot {

	private final ExternalProductGroupFinder externalProductGroupFinder;
	private final ExternalSiteSellerRelationFinder externalSiteSellerRelationFinder;
	private final ExternalProductGroupRegister externalProductGroupRegister;
	private final SiteRequestQueueManager siteRequestQueueManager;
	private final SiteRequestProcessorExecutor siteRequestProcessorExecutor;

	public ExternalProductGroupAggregateRoot(ExternalProductGroupFinder externalProductGroupFinder,
											 ExternalSiteSellerRelationFinder externalSiteSellerRelationFinder,
											 ExternalProductGroupRegister externalProductGroupRegister,
											 SiteRequestQueueManager siteRequestQueueManager,
											 SiteRequestProcessorExecutor siteRequestProcessorExecutor) {
		this.externalProductGroupFinder = externalProductGroupFinder;
		this.externalSiteSellerRelationFinder = externalSiteSellerRelationFinder;
		this.externalProductGroupRegister = externalProductGroupRegister;
		this.siteRequestQueueManager = siteRequestQueueManager;
		this.siteRequestProcessorExecutor = siteRequestProcessorExecutor;
	}

	public void syncExternalProductGroup(long siteId, SyncStatus status, ProductDomainEventType eventType, int size){
		List<ExternalProductGroup> externalProductGroups = externalProductGroupFinder.fetchBySiteIdAndStatus(siteId, status, size);

		if(!externalProductGroups.isEmpty()){
			externalProductGroups.forEach(productGroup ->
			siteRequestProcessorExecutor.processRequests(productGroup, eventType));
		}
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

	public void updateExternalProductGroupSyncRequired(long sellerId, long productGroupId, long brandId, long categoryId) {
		externalSiteSellerRelationFinder.fetchBySellerId(sellerId).ifPresent(relation -> {
			List<Long> siteIds = relation.externalSites()
				.stream()
				.map(ExternalSite::siteId)
				.toList();

			List<ExternalProductGroup> externalProductGroups = externalProductGroupFinder.fetchByProductGroupIdsAndSiteIds(productGroupId, siteIds, SyncStatus.getSyncStatus());

			List<ExternalProductGroup> updatedProductGroups = externalProductGroups.stream()
				.map(existingGroup -> new ExternalProductGroup.Builder(existingGroup)
					.status(SyncStatus.SYNC_REQUIRED)
					.sellerId(sellerId)
					.brandId(brandId)
					.categoryId(categoryId)
					.build())
				.toList();

			externalProductGroupRegister.updateAll(updatedProductGroups);
		});
	}


	public void requestExternalSite(long sellerId, long productGroupId, ProductDomainEventType productDomainEventType) {
		externalSiteSellerRelationFinder.fetchBySellerId(sellerId).ifPresent(relation -> {
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

	public void updateExternalProductGroupFailed(long siteId, long productGroupId) {

		ExternalProductGroup externalProductGroup = externalProductGroupFinder.fetchBySiteIdAndProductGroupId(siteId,
			productGroupId);

		ExternalProductGroup failedExternalProductGroup = ExternalProductGroupResponseFactory.failStatus(externalProductGroup);
		externalProductGroupRegister.update(failedExternalProductGroup);
	}


}
