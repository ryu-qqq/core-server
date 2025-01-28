package com.ryuqq.core.domain.external.core;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.exception.AsyncDomainException;
import com.ryuqq.core.domain.external.ExternalProduct;
import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.external.ExternalProductGroupRegister;
import com.ryuqq.core.domain.external.ExternalProductRegister;
import com.ryuqq.core.enums.ErrorType;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.events.ExternalProductGroupFailedEvent;

@Component
public class SiteRequestProcessResponseHandler {

	private static final Logger log = LoggerFactory.getLogger(SiteRequestProcessResponseHandler.class);

	private final ExternalProductGroupEventPublisher eventPublisher;
	private final ExternalProductGroupRegister externalProductGroupRegister;
	private final ExternalProductRegister externalProductRegister;

	public SiteRequestProcessResponseHandler(ExternalProductGroupEventPublisher eventPublisher,
											 ExternalProductGroupRegister externalProductGroupRegister,
											 ExternalProductRegister externalProductRegister) {
		this.eventPublisher = eventPublisher;
		this.externalProductGroupRegister = externalProductGroupRegister;
		this.externalProductRegister = externalProductRegister;
	}


	public void handleResponse(ProductDomainEventType productDomainEventType,
							   ExternalProductGroup productGroup,
							   ExternalMallProductGroupRequestResponse response) {
		try {
			ExternalProductGroup externalProductGroup = ExternalProductGroupResponseFactory.mapResponse(
				productDomainEventType, productGroup, response);
			externalProductGroupRegister.update(externalProductGroup);

			if (!response.getProductIdMappings().isEmpty()) {
				List<ExternalProduct> externalProducts = ExternalProductResponseFactory.mapResponse(
					productGroup.getExternalProductGroupId(), response.getProductIdMappings());
				externalProductRegister.updateAll(externalProducts);
			}

		} catch (Exception e) {
			handleFailure(productGroup, e);
		}
	}

	public void handleFailure(ExternalProductGroup productGroup, Exception e) {
		AsyncDomainException asyncDomainException = new AsyncDomainException(ErrorType.UNEXPECTED_ERROR,
			"Failed to process product group with ID: " + productGroup.getProductGroupId(), e);

		eventPublisher.publishEvent(new ExternalProductGroupFailedEvent(productGroup.getSiteId(), productGroup.getProductGroupId(), asyncDomainException));
		throw asyncDomainException;
	}


}
