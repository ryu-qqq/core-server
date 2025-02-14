package com.ryuqq.core.domain.external.dao.history;

public interface ExternalProductSyncRequestPersistenceRepository {

	void save(ExternalProductSyncCommand externalProductSyncCommand);
}
