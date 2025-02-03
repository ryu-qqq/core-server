package com.ryuqq.core.domain.external.dao;

public record ExternalProductUpdateRequest(
	long productGroupId,
	long siteId,
	String externalIdx
) {
}
