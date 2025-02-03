package com.ryuqq.core.storage.db.external;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.external.dao.group.ExternalProductGroupStatusRepository;
import com.ryuqq.core.enums.SyncStatus;

@Repository
public class DefaultExternalProductGroupStatusUpdateRepository implements ExternalProductGroupStatusRepository {

	private final ExternalProductGroupStatusUpdateQueryDslRepository externalProductGroupStatusUpdateQueryDslRepository;

	public DefaultExternalProductGroupStatusUpdateRepository(
		ExternalProductGroupStatusUpdateQueryDslRepository externalProductGroupStatusUpdateQueryDslRepository) {
		this.externalProductGroupStatusUpdateQueryDslRepository = externalProductGroupStatusUpdateQueryDslRepository;
	}

	@Override
	public long updateExternalProductGroupStatus(Long productGroupId, long siteId, String externalProductGroupId,
												SyncStatus syncStatus) {
		return externalProductGroupStatusUpdateQueryDslRepository.updateExternalProductGroupStatus(
			productGroupId, siteId, externalProductGroupId, syncStatus
		);
	}
}
