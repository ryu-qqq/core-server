package com.ryuqq.core.domain.external;


import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.dao.site.ExternalSiteQueryRepository;
import com.ryuqq.core.enums.SiteName;

@Component
public class ExternalSiteFinder {

	private final ExternalSiteQueryRepository externalSiteQueryRepository;

	public ExternalSiteFinder(ExternalSiteQueryRepository externalSiteQueryRepository) {
		this.externalSiteQueryRepository = externalSiteQueryRepository;
	}

	public ExternalSite fetchBySiteName(SiteName siteName) {
		return externalSiteQueryRepository.fetchBySiteName(siteName);
	}

}
