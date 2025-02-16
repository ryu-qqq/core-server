package com.ryuqq.core.storage.db.external;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.external.dao.history.ExternalProductSyncCommand;
import com.ryuqq.core.domain.external.dao.history.ExternalProductSyncRequestPersistenceRepository;

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
