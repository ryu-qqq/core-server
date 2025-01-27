package com.ryuqq.core.domain.external.core;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.domain.external.ExternalSite;
import com.ryuqq.core.enums.ProductDomainEventType;

public interface SiteRequestProcessor {

	boolean supportsSite(ExternalSite site);
	ExternalMallProductGroupRequestResponse process(ProductDomainEventType productDomainEventType, ExternalProductGroup externalProductGroup);

}
