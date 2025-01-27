package com.ryuqq.core.domain.external.core;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.ExternalProduct;
import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.external.ExternalProductGroupRegister;
import com.ryuqq.core.domain.external.ExternalProductRegister;
import com.ryuqq.core.enums.ProductDomainEventType;

@Component
public class SiteRequestProcessResponseHandler {

	private static final Logger log = LoggerFactory.getLogger(SiteRequestProcessResponseHandler.class);


	private final ExternalProductGroupRegister externalProductGroupRegister;
	private final ExternalProductRegister externalProductRegister;

	public SiteRequestProcessResponseHandler(ExternalProductGroupRegister externalProductGroupRegister,
									   ExternalProductRegister externalProductRegister) {
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

			log.info("Successfully processed response for product group: {}", productGroup.getProductGroupId());
		} catch (Exception e) {
			log.error("Failed to process response for product group: {}", productGroup.getProductGroupId(), e);
			handleFailure(productGroup, e);
		}
	}

	public void handleFailure(ExternalProductGroup productGroup, Exception e) {
		try {
			ExternalProductGroup failedGroup = ExternalProductGroupResponseFactory.failStatus(productGroup);
			externalProductGroupRegister.update(failedGroup);
			log.info("Updated product group to failure status: {}", productGroup.getProductGroupId());
		} catch (Exception ex) {
			log.error("Failed to update product group to failure status: {}", productGroup.getProductGroupId(), ex);
		}
	}

}
