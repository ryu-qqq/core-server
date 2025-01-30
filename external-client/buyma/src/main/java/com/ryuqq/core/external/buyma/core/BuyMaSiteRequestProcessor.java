package com.ryuqq.core.external.buyma.core;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.external.ExternalSite;
import com.ryuqq.core.domain.external.core.ExternalMallProductGroupRequestResponse;
import com.ryuqq.core.domain.external.core.SiteRequestProcessor;
import com.ryuqq.core.domain.external.core.UpdateTypeHandler;
import com.ryuqq.core.enums.ErrorType;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.enums.SiteName;
import com.ryuqq.core.external.ExternalSiteException;

@Component
public class BuyMaSiteRequestProcessor implements SiteRequestProcessor {

	private final List<UpdateTypeHandler> updateHandlers;

	public BuyMaSiteRequestProcessor(List<UpdateTypeHandler> updateHandlers) {
		this.updateHandlers = updateHandlers;
	}

	@Override
	public boolean supportsSite(ExternalSite site) {
		return site.siteName() == SiteName.OCO;
	}

	@Override
	public ExternalMallProductGroupRequestResponse process(ProductDomainEventType productDomainEventType, ExternalProductGroup externalProductGroup) {
		UpdateTypeHandler handler = updateHandlers.stream()
			.filter(h -> h.supports(SiteName.BUYMA, productDomainEventType))
			.findFirst()
			.orElseThrow(() -> new ExternalSiteException(ErrorType.UNEXPECTED_ERROR,  "No handler found for update type: " + productDomainEventType));

		return handler.handle(externalProductGroup);
	}
}
