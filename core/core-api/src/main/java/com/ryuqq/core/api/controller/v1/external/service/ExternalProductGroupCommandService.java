package com.ryuqq.core.api.controller.v1.external.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ryuqq.core.domain.external.ExternalProductGroupRegisterHandler;
import com.ryuqq.core.domain.external.ExternalProductGroupUpdateHandler;
import com.ryuqq.core.domain.external.core.ExternalProductGroupAggregateRoot;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.enums.SyncStatus;

@Transactional
@Service
public class ExternalProductGroupCommandService {

	private final ExternalProductGroupRegisterHandler externalProductGroupRegisterHandler;
	private final ExternalProductGroupUpdateHandler externalProductGroupUpdateHandler;
	private final ExternalProductGroupAggregateRoot externalProductGroupAggregateRoot;

	public ExternalProductGroupCommandService(ExternalProductGroupRegisterHandler externalProductGroupRegisterHandler,
											  ExternalProductGroupUpdateHandler externalProductGroupUpdateHandler,
											  ExternalProductGroupAggregateRoot externalProductGroupAggregateRoot) {
		this.externalProductGroupRegisterHandler = externalProductGroupRegisterHandler;
		this.externalProductGroupUpdateHandler = externalProductGroupUpdateHandler;
		this.externalProductGroupAggregateRoot = externalProductGroupAggregateRoot;
	}

	public void syncExternalProductGroup(long siteId, SyncStatus status, ProductDomainEventType eventType, int size){
		externalProductGroupAggregateRoot.syncExternalProductGroup(siteId, status, eventType, size);
	}

	public void registerExternalProductGroup(long sellerId, long productGroupId, long brandId, long categoryId){
		externalProductGroupRegisterHandler.registerExternalProductGroupWaitingStatus(sellerId, productGroupId, brandId, categoryId);
	}

	public void updateExternalProductGroup(long sellerId, long productGroupId, ProductDomainEventType productDomainEventType){
		externalProductGroupUpdateHandler.updateExternalProductGroup(sellerId, productGroupId, productDomainEventType);
	}

	public void updateFailedExternalProductGroup(long siteId, long productGroupId){
		externalProductGroupAggregateRoot.updateExternalProductGroupFailed(siteId, productGroupId);
	}

}
