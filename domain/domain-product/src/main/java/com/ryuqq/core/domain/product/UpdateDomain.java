package com.ryuqq.core.domain.product;

import com.ryuqq.core.enums.ProductDomainEventType;

public record UpdateDomain<T>(

	T domain,
	ProductDomainEventType productDomainEventType,
	boolean isRealTime

) {}
