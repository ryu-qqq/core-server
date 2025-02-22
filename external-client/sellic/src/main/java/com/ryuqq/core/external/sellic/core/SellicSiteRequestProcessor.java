package com.ryuqq.core.external.sellic.core;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.external.core.ExternalMallProductGroupRequestResponse;
import com.ryuqq.core.domain.external.core.SiteRequestProcessor;
import com.ryuqq.core.domain.external.core.UpdateTypeHandler;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.enums.SiteName;

@Component
public class SellicSiteRequestProcessor implements SiteRequestProcessor {

	@Override
	public boolean supportsSite(SiteName siteName) {
		return siteName == SiteName.SELLIC;
	}

	@Override
	public SiteName getSupportedSite() {
		return SiteName.SELLIC;
	}

	@Override
	public CompletableFuture<ExternalMallProductGroupRequestResponse> process(
		ProductDomainEventType productDomainEventType, ExternalProductGroup externalProductGroup,
		ExecutorService executor,
		List<UpdateTypeHandler<? extends ExternalMallProductGroupRequestResponse>> updateHandlers) {
		return SiteRequestProcessor.super.process(productDomainEventType, externalProductGroup, executor,
			updateHandlers);
	}


}
