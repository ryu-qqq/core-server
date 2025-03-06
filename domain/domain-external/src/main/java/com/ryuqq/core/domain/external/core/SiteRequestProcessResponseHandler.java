package com.ryuqq.core.domain.external.core;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.exception.DomainException;
import com.ryuqq.core.domain.external.ExternalProduct;
import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.external.ExternalProductGroupEventPublisher;
import com.ryuqq.core.domain.external.ExternalProductGroupRegister;
import com.ryuqq.core.domain.external.ExternalProductRegister;
import com.ryuqq.core.domain.external.ExternalProductSyncRequestRegister;
import com.ryuqq.core.enums.ErrorType;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.events.ExternalProductGroupFailedEvent;

@Component
public class SiteRequestProcessResponseHandler {

	private final ExternalProductGroupEventPublisher eventPublisher;
	private final ExternalProductGroupRegister externalProductGroupRegister;
	private final ExternalProductRegister externalProductRegister;
	private final ExternalProductSyncRequestRegister externalProductSyncRequestRegister;


	public SiteRequestProcessResponseHandler(ExternalProductGroupEventPublisher eventPublisher,
											 ExternalProductGroupRegister externalProductGroupRegister,
											 ExternalProductRegister externalProductRegister,
											 ExternalProductSyncRequestRegister externalProductSyncRequestRegister) {
		this.eventPublisher = eventPublisher;
		this.externalProductGroupRegister = externalProductGroupRegister;
		this.externalProductRegister = externalProductRegister;
		this.externalProductSyncRequestRegister = externalProductSyncRequestRegister;
	}


	public void handleResponse(ProductDomainEventType productDomainEventType,
							   ExternalProductGroup productGroup,
							   ExternalMallProductGroupRequestResponse response) {
		try {
			ExternalProductGroup externalProductGroup = ExternalProductGroupResponseFactory.mapResponse(
				productDomainEventType, productGroup, response);
			externalProductGroupRegister.update(externalProductGroup);

			// externalProductSyncRequestRegister.register(
			// 	externalProductGroup.getSiteId(), externalProductGroup.getProductGroupId(),
			// 	response.getExternalProductGroupId(), true
			// );

			if (!response.getProductIdMappings().isEmpty()) {
				List<ExternalProduct> externalProducts = ExternalProductResponseFactory.mapResponse(
					productGroup.getExternalProductGroupId(), response.getProductIdMappings());
				externalProductRegister.updateAll(externalProducts);
			}

		} catch (Exception e) {
			handleFailure(productGroup, e);
		}
	}

	public void handleFailure(ExternalProductGroup externalProductGroup, Throwable e) {
		DomainException domainException = new DomainException(ErrorType.UNEXPECTED_ERROR, e);
		eventPublisher.publishEvent(new ExternalProductGroupFailedEvent(externalProductGroup.getSiteId(), externalProductGroup.getProductGroupId(), domainException));
		//externalProductSyncRequestRegister.register(externalProductGroup.getSiteId(), externalProductGroup.getProductGroupId(), null, false);
	}


}
