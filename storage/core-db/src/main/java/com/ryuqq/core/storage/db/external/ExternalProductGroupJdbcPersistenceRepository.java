package com.ryuqq.core.storage.db.external;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.external.dao.group.ExternalProductGroupCommand;
import com.ryuqq.core.domain.external.dao.group.ExternalProductGroupPersistenceRepository;

@Repository
public class ExternalProductGroupJdbcPersistenceRepository implements ExternalProductGroupPersistenceRepository {

	private final ExternalProductGroupStorageMapper externalProductGroupStorageMapper;
	private final ExternalProductGroupJdbcRepository externalProductGroupJdbcRepository;

	public ExternalProductGroupJdbcPersistenceRepository(
		ExternalProductGroupStorageMapper externalProductGroupStorageMapper,
		ExternalProductGroupJdbcRepository externalProductGroupJdbcRepository) {
		this.externalProductGroupStorageMapper = externalProductGroupStorageMapper;
		this.externalProductGroupJdbcRepository = externalProductGroupJdbcRepository;
	}

	@Override
	public void save(ExternalProductGroupCommand externalProductGroupCommand){
		saveAll(List.of(externalProductGroupCommand));
	}

	@Override
	public void saveAll(List<ExternalProductGroupCommand> externalProductGroupCommands) {
		List<ExternalProductGroupEntity> entities = getEntities(externalProductGroupCommands);
		externalProductGroupJdbcRepository.batchInsertExternalProductGroups(entities);
	}

	@Override
	public void update(ExternalProductGroupCommand externalProductGroupCommand) {
		updateAll(List.of(externalProductGroupCommand));
	}

	@Override
	public void updateAll(List<ExternalProductGroupCommand> externalProductGroupCommands) {
		List<ExternalProductGroupEntity> entities = getEntities(externalProductGroupCommands);
		externalProductGroupJdbcRepository.batchUpdateProductGroups(entities);
	}

	private List<ExternalProductGroupEntity> getEntities(List<ExternalProductGroupCommand> externalProductGroupCommands) {
		return externalProductGroupCommands.stream().map(externalProductGroupStorageMapper::toEntity).toList();
	}

}
