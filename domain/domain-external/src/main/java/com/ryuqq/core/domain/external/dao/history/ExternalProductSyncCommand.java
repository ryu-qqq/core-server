package com.ryuqq.core.domain.external.dao.history;

public interface ExternalProductSyncCommand {

	String traceId();
	long siteId();
	long productGroupId();
	String externalProductGroupId();
	boolean syncResult();
}
