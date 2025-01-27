package com.ryuqq.core.domain.external;

import com.ryuqq.core.enums.SiteName;

public record ExternalSite(
	long siteId,
	SiteName siteName
) {
}
