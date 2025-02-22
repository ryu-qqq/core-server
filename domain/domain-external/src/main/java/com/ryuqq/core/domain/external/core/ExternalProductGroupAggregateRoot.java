package com.ryuqq.core.domain.external.core;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.external.ExternalProductGroupFinder;
import com.ryuqq.core.domain.external.ExternalProductGroupRegister;
import com.ryuqq.core.domain.external.ExternalSiteSellerRelationFinder;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.enums.SyncStatus;

@Service
public class ExternalProductGroupAggregateRoot {

	private final ExternalProductGroupFinder externalProductGroupFinder;
	private final ExternalSiteSellerRelationFinder externalSiteSellerRelationFinder;
	private final ExternalProductGroupRegister externalProductGroupRegister;
	private final SiteRequestProcessorExecutor siteRequestProcessorExecutor;

	public ExternalProductGroupAggregateRoot(ExternalProductGroupFinder externalProductGroupFinder,
											 ExternalSiteSellerRelationFinder externalSiteSellerRelationFinder,
											 ExternalProductGroupRegister externalProductGroupRegister,
											 SiteRequestProcessorExecutor siteRequestProcessorExecutor) {
		this.externalProductGroupFinder = externalProductGroupFinder;
		this.externalSiteSellerRelationFinder = externalSiteSellerRelationFinder;
		this.externalProductGroupRegister = externalProductGroupRegister;
		this.siteRequestProcessorExecutor = siteRequestProcessorExecutor;
	}

	public void syncExternalProductGroup(long siteId, SyncStatus status, ProductDomainEventType eventType, int size){
		List<ExternalProductGroup> externalProductGroups = externalProductGroupFinder.fetchBySiteIdAndStatus(siteId, status, size);

		if(!externalProductGroups.isEmpty()){
			externalProductGroups.forEach(productGroup ->
			siteRequestProcessorExecutor.processRequests(productGroup, eventType));
		}
	}


	public void updateExternalProductGroupFailed(long siteId, long productGroupId) {

		ExternalProductGroup externalProductGroup = externalProductGroupFinder.fetchBySiteIdAndProductGroupId(siteId,
			productGroupId);

		ExternalProductGroup failedExternalProductGroup = ExternalProductGroupResponseFactory.failStatus(externalProductGroup);
		externalProductGroupRegister.update(failedExternalProductGroup);
	}




}
