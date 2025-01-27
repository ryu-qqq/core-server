package com.ryuqq.core.domain.external;

import java.util.List;

public record ExternalSiteSellerRelation(
	long sellerId,
	String sellerName,
	List<ExternalSite> externalSites
) {}
