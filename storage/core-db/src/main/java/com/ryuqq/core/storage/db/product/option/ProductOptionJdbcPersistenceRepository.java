package com.ryuqq.core.storage.db.product.option;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.product.dao.options.OptionContextCommand;
import com.ryuqq.core.domain.product.dao.options.mapping.ProductOptionPersistenceRepository;

@Repository
public class ProductOptionJdbcPersistenceRepository implements ProductOptionPersistenceRepository {

	private final OptionStorageMapper optionStorageMapper;
	private final ProductOptionJdbcRepository productOptionJdbcRepository;

	public ProductOptionJdbcPersistenceRepository(OptionStorageMapper optionStorageMapper,
												  ProductOptionJdbcRepository productOptionJdbcRepository) {
		this.optionStorageMapper = optionStorageMapper;
		this.productOptionJdbcRepository = productOptionJdbcRepository;
	}

	@Override
	public void save(OptionContextCommand optionContextCommand) {
		saveAll(List.of(optionContextCommand));
	}

	@Override
	public void saveAll(List<OptionContextCommand> optionContextCommands) {
		List<ProductOptionEntity> entities = getEntities(optionContextCommands);
		productOptionJdbcRepository.batchInsertProductOptions(entities);
	}

	@Override
	public void update(OptionContextCommand optionContextCommand) {
		updateAll(List.of(optionContextCommand));
	}

	@Override
	public void updateAll(List<OptionContextCommand> optionContextCommands) {
		List<ProductOptionEntity> entities = getEntities(optionContextCommands);
		productOptionJdbcRepository.batchUpdateProductOptions(entities);
	}

	@Override
	public void deleteAll(List<Long> productIds) {
		productOptionJdbcRepository.softDeleteAll(productIds);
	}

	private List<ProductOptionEntity> getEntities(List<OptionContextCommand> optionContextCommands) {
		return optionContextCommands.stream().map(optionStorageMapper::toEntity).toList();
	}


}
