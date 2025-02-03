package com.ryuqq.core.storage.db.product.group;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.product.dao.group.ProductGroupCommand;
import com.ryuqq.core.domain.product.dao.group.ProductGroupPersistenceRepository;

@Repository
public class ProductGroupHybirdPersistenceRepository implements ProductGroupPersistenceRepository {

	private final ProductGroupStorageMapper productGroupStorageMapper;
	private final ProductGroupJdbcRepository productGroupJdbcRepository;
	private final ProductGroupJpaRepository productGroupJpaRepository;

	public ProductGroupHybirdPersistenceRepository(ProductGroupStorageMapper productGroupStorageMapper,
												   ProductGroupJdbcRepository productGroupJdbcRepository,
												   ProductGroupJpaRepository productGroupJpaRepository) {
		this.productGroupStorageMapper = productGroupStorageMapper;
		this.productGroupJdbcRepository = productGroupJdbcRepository;
		this.productGroupJpaRepository = productGroupJpaRepository;
	}

	@Override
	public long save(ProductGroupCommand productGroupCommand){
		List<ProductGroupEntity> entities = getEntities(List.of(productGroupCommand));
		return productGroupJpaRepository.save(entities.getFirst()).getId();
	}

	@Override
	public List<Long> saveAll(List<ProductGroupCommand> productGroupCommands) {
		List<ProductGroupEntity> entities = getEntities(productGroupCommands);
		return productGroupJdbcRepository.batchInsertProductGroupsAndGetIds(entities);
	}

	@Override
	public void update(ProductGroupCommand productGroupCommand) {
		updateAll(List.of(productGroupCommand));
	}

	@Override
	public void updateAll(List<ProductGroupCommand> productGroupCommands) {
		List<ProductGroupEntity> entities = getEntities(productGroupCommands);
		productGroupJdbcRepository.batchUpdateProductGroups(entities);
	}

	private List<ProductGroupEntity> getEntities(List<ProductGroupCommand> productGroupCommands){
		return productGroupCommands.stream()
			.map(productGroupStorageMapper::toEntity)
			.toList();
	}

}
