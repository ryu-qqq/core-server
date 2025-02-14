package com.ryuqq.core.storage.db.external;

import com.ryuqq.core.domain.external.dao.history.ExternalProductSyncCommand;
import com.ryuqq.core.domain.external.dao.history.ExternalProductSyncRequestPersistenceRepository;

import org.springframework.stereotype.Repository;

@Repository
public class ExternalProductSyncRequestJpaPersistenceRepository implements
	ExternalProductSyncRequestPersistenceRepository {

	private final ExternalProductSyncRequestJpaRepository externalProductSyncRequestJpaRepository;

	public ExternalProductSyncRequestJpaPersistenceRepository(
		ExternalProductSyncRequestJpaRepository externalProductSyncRequestJpaRepository) {
		this.externalProductSyncRequestJpaRepository = externalProductSyncRequestJpaRepository;
	}

	@Override
	public void save(ExternalProductSyncCommand externalProductSyncCommand){
		ExternalProductSyncRequestEntity entity = ExternalProductSyncRequestStorageMapper.toEntity(externalProductSyncCommand);
		externalProductSyncRequestJpaRepository.save(entity);
	}

}
