package com.ryuqq.core.storage.db.product.option;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.product.dao.options.detail.OptionDetailCommand;
import com.ryuqq.core.domain.product.dao.options.detail.OptionDetailPersistenceRepository;

@Repository
public class OptionDetailHybridPersistenceRepository implements OptionDetailPersistenceRepository {

	private final OptionStorageMapper optionStorageMapper;
	private final OptionDetailJdbcRepository optionDetailJdbcRepository;
	private final OptionDetailJpaRepository optionDetailJpaRepository;

	public OptionDetailHybridPersistenceRepository(OptionStorageMapper optionStorageMapper,
												   OptionDetailJdbcRepository optionDetailJdbcRepository,
												   OptionDetailJpaRepository optionDetailJpaRepository) {
		this.optionStorageMapper = optionStorageMapper;
		this.optionDetailJdbcRepository = optionDetailJdbcRepository;
		this.optionDetailJpaRepository = optionDetailJpaRepository;
	}

	@Override
	public long save(OptionDetailCommand optionDetailCommand) {
		OptionDetailEntity optionDetailEntity = getEntities(List.of(optionDetailCommand)).getFirst();
		return optionDetailJpaRepository.save(optionDetailEntity).getId();
	}

	@Override
	public List<Long> saveAll(List<OptionDetailCommand> optionDetailCommands) {
		List<OptionDetailEntity> entities = getEntities(optionDetailCommands);
		return optionDetailJdbcRepository.batchInsertOptionDetailsAndGetIds(entities);
	}

	@Override
	public void update(OptionDetailCommand optionDetailCommand) {
		updateAll(List.of(optionDetailCommand));
	}

	@Override
	public void updateAll(List<OptionDetailCommand> optionDetailCommands) {
		List<OptionDetailEntity> entities = getEntities(optionDetailCommands);
		optionDetailJdbcRepository.batchUpdateOptionDetails(entities);

	}

	private List<OptionDetailEntity> getEntities(List<OptionDetailCommand> optionDetailCommands){
		return optionDetailCommands.stream().map(optionStorageMapper::toEntity).toList();
	}

}
