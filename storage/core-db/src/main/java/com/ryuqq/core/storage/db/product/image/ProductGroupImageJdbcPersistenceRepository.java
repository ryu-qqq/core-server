package com.ryuqq.core.storage.db.product.image;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.product.dao.image.ProductGroupImageCommand;
import com.ryuqq.core.domain.product.dao.image.ProductGroupImagePersistenceRepository;

@Repository
public class ProductGroupImageJdbcPersistenceRepository implements ProductGroupImagePersistenceRepository {

	private final ProductGroupImageStorageMapper productGroupImageStorageMapper;
	private final ProductGroupImageJdbcRepository productGroupImageJdbcRepository;

	public ProductGroupImageJdbcPersistenceRepository(ProductGroupImageStorageMapper productGroupImageStorageMapper,
													  ProductGroupImageJdbcRepository productGroupImageJdbcRepository) {
		this.productGroupImageStorageMapper = productGroupImageStorageMapper;
		this.productGroupImageJdbcRepository = productGroupImageJdbcRepository;
	}

	@Override
	public void save(ProductGroupImageCommand productGroupImage) {
		saveAll(List.of(productGroupImage));
	}

	@Override
	public void saveAll(List<? extends ProductGroupImageCommand> productGroupImageCommands) {
		List<ProductGroupImageEntity> entities = getEntities(productGroupImageCommands);
		productGroupImageJdbcRepository.batchInsertProductGroupImages(entities);
	}

	@Override
	public void update(ProductGroupImageCommand productGroupImage) {
		updateAll(List.of(productGroupImage));
	}


	@Override
	public void updateAll(List<? extends ProductGroupImageCommand> productGroupImageCommands) {
		List<ProductGroupImageEntity> entities = getEntities(productGroupImageCommands);
		productGroupImageJdbcRepository.batchUpdateProductGroups(entities);
	}


	private List<ProductGroupImageEntity> getEntities(List<? extends ProductGroupImageCommand> productGroupImageCommands) {
		return productGroupImageCommands.stream()
			.map(productGroupImageStorageMapper::toEntity)
			.toList();
	}
}
