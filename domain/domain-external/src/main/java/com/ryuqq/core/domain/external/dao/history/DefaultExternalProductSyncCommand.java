package com.ryuqq.core.domain.external.dao.history;

public record DefaultExternalProductSyncCommand(
	String traceId,
	long siteId,
	long productGroupId,
	String externalProductGroupId,
	boolean syncResult

) implements ExternalProductSyncCommand{}
