package com.ryuqq.core.domain.external.core;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.enums.SiteName;

public interface SiteRequestProcessor {

	boolean supportsSite(SiteName siteName);
	ExternalMallProductGroupRequestResponse process(ProductDomainEventType productDomainEventType, ExternalProductGroup externalProductGroup);

}
