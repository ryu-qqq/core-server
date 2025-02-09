package com.ryuqq.core.storage.db.product.option;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.product.core.OptionGroupCommand;
import com.ryuqq.core.domain.product.dao.options.group.OptionGroupPersistenceRepository;

@Repository
public class OptionGroupHybirdPersistenceRepository implements OptionGroupPersistenceRepository {

	private final OptionStorageMapper optionStorageMapper;
	private final OptionGroupJpaRepository optionGroupJpaRepository;
	private final OptionGroupJdbcRepository optionGroupJdbcRepository;

	public OptionGroupHybirdPersistenceRepository(OptionStorageMapper optionStorageMapper, OptionGroupJpaRepository optionGroupJpaRepository,
												  OptionGroupJdbcRepository optionGroupJdbcRepository) {
		this.optionStorageMapper = optionStorageMapper;
		this.optionGroupJpaRepository = optionGroupJpaRepository;
		this.optionGroupJdbcRepository = optionGroupJdbcRepository;
	}

	@Override
	public long save(OptionGroupCommand optionGroupCommand) {
		OptionGroupEntity optionGroupEntity = getEntities(List.of(optionGroupCommand)).getFirst();
		return optionGroupJpaRepository.save(optionGroupEntity).getId();
	}

	@Override
	public List<Long> saveAll(List<OptionGroupCommand> optionGroupCommands) {
		List<OptionGroupEntity> entities = getEntities(optionGroupCommands);
		return optionGroupJdbcRepository.batchInsertOptionGroupsAndGetIds(entities);
	}

	@Override
	public void update(OptionGroupCommand optionGroupCommand) {
		updateAll(List.of(optionGroupCommand));
	}

	@Override
	public void updateAll(List<OptionGroupCommand> optionGroupCommands) {
		List<OptionGroupEntity> entities = getEntities(optionGroupCommands);
		optionGroupJdbcRepository.batchUpdateOptionDetails(entities);
	}

	private List<OptionGroupEntity> getEntities(List<OptionGroupCommand> optionGroupCommands){
		return optionGroupCommands.stream().map(optionStorageMapper::toEntity).toList();
	}


}
