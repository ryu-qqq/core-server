package com.ryuqq.core.storage.db.product.option;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.product.dao.options.mapping.ProductOptionCommand;
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
	public void save(ProductOptionCommand productOptionCommand) {
		saveAll(List.of(productOptionCommand));
	}

	@Override
	public void saveAll(List<ProductOptionCommand> productOptionCommands) {
		List<ProductOptionEntity> entities = getEntities(productOptionCommands);
		productOptionJdbcRepository.batchInsertProductOptions(entities);
	}

	@Override
	public void update(ProductOptionCommand productOptionCommand) {
		updateAll(List.of(productOptionCommand));
	}

	@Override
	public void updateAll(List<ProductOptionCommand> productOptionCommands) {
		List<ProductOptionEntity> entities = getEntities(productOptionCommands);
		productOptionJdbcRepository.batchUpdateProductOptions(entities);
	}

	@Override
	public void deleteAll(List<Long> productIds) {
		productOptionJdbcRepository.softDeleteAll(productIds);
	}

	private List<ProductOptionEntity> getEntities(List<ProductOptionCommand> productOptionCommands) {
		return productOptionCommands.stream().map(optionStorageMapper::toEntity).toList();
	}


}
