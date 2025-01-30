package com.ryuqq.core.domain.external.dao.group;


import com.ryuqq.core.enums.SyncStatus;

public interface ExternalProductGroupStatusRepository {

	long updateExternalProductGroupStatus(Long productGroupId, long siteId, String externalProductGroupId, SyncStatus syncStatus);
}
