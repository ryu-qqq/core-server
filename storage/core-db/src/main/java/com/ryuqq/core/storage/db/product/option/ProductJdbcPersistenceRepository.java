package com.ryuqq.core.storage.db.product.option;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.product.core.ProductCommand;
import com.ryuqq.core.domain.product.dao.options.ProductPersistenceRepository;

@Repository
public class ProductJdbcPersistenceRepository implements ProductPersistenceRepository {

	private final ProductStorageMapper productStorageMapper;
	private final ProductJdbcRepository productJdbcRepository;
	private final ProductJpaRepository productJpaRepository;

	public ProductJdbcPersistenceRepository(ProductStorageMapper productStorageMapper,
											ProductJdbcRepository productJdbcRepository,
											ProductJpaRepository productJpaRepository) {
		this.productStorageMapper = productStorageMapper;
		this.productJdbcRepository = productJdbcRepository;
		this.productJpaRepository = productJpaRepository;
	}

	@Override
	public long save(ProductCommand productCommand){
		ProductEntity productEntity = getEntities(List.of(productCommand)).getFirst();
		return productJpaRepository.save(productEntity).getId();
	}

	@Override
	public List<Long> saveAll(List<ProductCommand> productCommands) {
		List<ProductEntity> entities = getEntities(productCommands);
		return productJdbcRepository.batchInsertProductsAndGetIds(entities);
	}

	@Override
	public void update(ProductCommand productCommand) {
		updateAll(List.of(productCommand));
	}

	@Override
	public void updateAll(List<ProductCommand> productCommands) {
		List<ProductEntity> entities = getEntities(productCommands);
		productJdbcRepository.batchUpdateProducts(entities);
	}

	private List<ProductEntity> getEntities(List<ProductCommand> productCommands) {
		return productCommands.stream().map(productStorageMapper::toEntity).toList();
	}

}
