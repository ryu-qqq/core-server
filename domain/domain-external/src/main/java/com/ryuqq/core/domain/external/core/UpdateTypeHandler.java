package com.ryuqq.core.domain.external.core;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.enums.ProductDomainEventType;
import com.ryuqq.core.enums.SiteName;

public interface UpdateTypeHandler {

	boolean supports(SiteName siteName, ProductDomainEventType productDomainEventType);
	ExternalMallProductGroupRequestResponse handle(ExternalProductGroup externalProductGroup);

}
