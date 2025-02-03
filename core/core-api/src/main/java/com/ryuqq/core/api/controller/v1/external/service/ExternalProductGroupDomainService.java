package com.ryuqq.core.api.controller.v1.external.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ryuqq.core.domain.external.core.ExternalProductGroupAggregateRoot;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.enums.SyncStatus;

@Transactional
@Service
public class ExternalProductGroupDomainService {

	private final ExternalProductGroupAggregateRoot externalProductGroupAggregateRoot;

	public ExternalProductGroupDomainService(ExternalProductGroupAggregateRoot externalProductGroupAggregateRoot) {
		this.externalProductGroupAggregateRoot = externalProductGroupAggregateRoot;
	}


	public void syncExternalProductGroup(long siteId, SyncStatus status){
		externalProductGroupAggregateRoot.syncExternalProductGroup(siteId, status);
	}

	public void registerExternalProductGroup(long sellerId, long productGroupId, long brandId, long categoryId){
		externalProductGroupAggregateRoot.registerExternalProductGroupWaitingStatus(sellerId, productGroupId, brandId, categoryId);
	}

	public void updateExternalProductGroup(long sellerId, long productGroupId, long brandId, long categoryId){
		externalProductGroupAggregateRoot.updateExternalProductGroupSyncRequired(sellerId, productGroupId, brandId, categoryId);
	}

	public void requestExternalSite(long sellerId, long productGroupId, ProductDomainEventType productDomainEventType){
		externalProductGroupAggregateRoot.requestExternalSite(sellerId, productGroupId, productDomainEventType);
	}

	public void updateFailedExternalProductGroup(long siteId, long productGroupId){
		externalProductGroupAggregateRoot.updateExternalProductGroupFailed(siteId, productGroupId);
	}

}
