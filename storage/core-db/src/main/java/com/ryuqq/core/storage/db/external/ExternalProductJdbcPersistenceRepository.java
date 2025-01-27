package com.ryuqq.core.storage.db.external;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.external.dao.options.ExternalProductCommand;
import com.ryuqq.core.domain.external.dao.options.ExternalProductPersistenceRepository;

@Repository
public class ExternalProductJdbcPersistenceRepository implements ExternalProductPersistenceRepository {

	private final ExternalProductStorageMapper externalProductStorageMapper;
	private final ExternalProductJdbcRepository externalProductJdbcRepository;

	public ExternalProductJdbcPersistenceRepository(ExternalProductStorageMapper externalProductStorageMapper,
													ExternalProductJdbcRepository externalProductJdbcRepository) {
		this.externalProductStorageMapper = externalProductStorageMapper;
		this.externalProductJdbcRepository = externalProductJdbcRepository;
	}

	@Override
	public void save(ExternalProductCommand externalProductCommand) {
		saveAll(List.of(externalProductCommand));
	}

	@Override
	public void saveAll(List<ExternalProductCommand> externalProductCommands) {
		List<ExternalProductEntity> externalProductEntities = externalProductCommands.stream()
			.map(externalProductStorageMapper::toEntity)
			.toList();

		externalProductJdbcRepository.batchInsertExternalProducts(externalProductEntities);
	}

	@Override
	public void update(ExternalProductCommand externalProductCommand) {
		updateAll(List.of(externalProductCommand));
	}

	@Override
	public void updateAll(List<ExternalProductCommand> externalProductCommands) {
		List<ExternalProductEntity> externalProductEntities = externalProductCommands.stream()
			.map(externalProductStorageMapper::toEntity)
			.toList();

		externalProductJdbcRepository.batchUpdateExternalProduct(externalProductEntities);
	}

}
