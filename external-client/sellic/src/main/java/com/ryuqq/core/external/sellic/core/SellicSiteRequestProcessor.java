package com.ryuqq.core.external.sellic.core;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.external.core.ExternalMallProductGroupRequestResponse;
import com.ryuqq.core.domain.external.core.SiteRequestProcessor;
import com.ryuqq.core.domain.external.core.UpdateTypeHandler;
import com.ryuqq.core.enums.ErrorType;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.enums.SiteName;
import com.ryuqq.core.external.ExternalSiteException;

@Service
public class SellicSiteRequestProcessor implements SiteRequestProcessor {

	private final List<UpdateTypeHandler> updateHandlers;

	public SellicSiteRequestProcessor(List<UpdateTypeHandler> updateHandlers) {
		this.updateHandlers = updateHandlers;
	}

	@Override
	public boolean supportsSite(SiteName siteName) {
		return siteName == SiteName.SELLIC;
	}

	@Override
	public ExternalMallProductGroupRequestResponse process(ProductDomainEventType productDomainEventType, ExternalProductGroup externalProductGroup) {
		UpdateTypeHandler handler = updateHandlers.stream()
			.filter(h -> h.supports(SiteName.SELLIC, productDomainEventType))
			.findFirst()
			.orElseThrow(() -> new ExternalSiteException(ErrorType.UNEXPECTED_ERROR, "No handler found for update type: " + productDomainEventType));

		return handler.handle(externalProductGroup);
	}

}
