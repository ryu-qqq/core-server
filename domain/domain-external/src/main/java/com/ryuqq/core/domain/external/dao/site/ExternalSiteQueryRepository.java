package com.ryuqq.core.domain.external.dao.site;

import java.util.List;
import java.util.Optional;

import com.ryuqq.core.domain.external.ExternalSiteSellerRelation;

public interface ExternalSiteQueryRepository {
	Optional<ExternalSiteSellerRelation> fetchBySellerId(long sellerId);
	List<ExternalSiteSellerRelation> fetchExternalSiteSellerRelation();

}
