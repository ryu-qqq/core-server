package com.ryuqq.core.domain.external.dao.site;

import java.util.List;
import java.util.Optional;

import com.ryuqq.core.domain.external.ExternalSite;
import com.ryuqq.core.domain.external.ExternalSiteSellerRelation;
import com.ryuqq.core.enums.SiteName;

public interface ExternalSiteQueryRepository {
	Optional<ExternalSiteSellerRelation> fetchBySellerId(long sellerId);
	List<ExternalSiteSellerRelation> fetchExternalSiteSellerRelation();
	ExternalSite fetchBySiteName(SiteName siteName);

}
