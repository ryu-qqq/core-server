package com.ryuqq.core.storage.db.product.image;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.product.dao.image.ProductDetailDescriptionCommand;
import com.ryuqq.core.domain.product.dao.image.ProductDetailDescriptionPersistenceRepository;

@Repository
public class ProductDetailDescriptionJdbcPersistenceRepository implements ProductDetailDescriptionPersistenceRepository {

	private final ProductDetailDescriptionStorageMapper productDetailDescriptionStorageMapper;
	private final ProductDetailDescriptionJdbcRepository productDetailDescriptionJdbcRepository;

	public ProductDetailDescriptionJdbcPersistenceRepository(
		ProductDetailDescriptionStorageMapper productDetailDescriptionStorageMapper,
		ProductDetailDescriptionJdbcRepository productDetailDescriptionJdbcRepository) {
		this.productDetailDescriptionStorageMapper = productDetailDescriptionStorageMapper;
		this.productDetailDescriptionJdbcRepository = productDetailDescriptionJdbcRepository;
	}

	@Override
	public void save(ProductDetailDescriptionCommand productDetailDescriptionCommand){
		saveAll(List.of(productDetailDescriptionCommand));
	}

	@Override
	public void saveAll(List<ProductDetailDescriptionCommand> productDetailDescriptionCommands) {
		List<ProductDetailDescriptionEntity> entities = getEntities(productDetailDescriptionCommands);
		productDetailDescriptionJdbcRepository.batchInsertProductDetailDescriptions(entities);
	}

	@Override
	public void update(ProductDetailDescriptionCommand productDetailDescriptionCommand) {
		updateAll(List.of(productDetailDescriptionCommand));
	}

	@Override
	public void updateAll(List<ProductDetailDescriptionCommand> productDetailDescriptionCommands) {
		List<ProductDetailDescriptionEntity> entities = getEntities(productDetailDescriptionCommands);
		productDetailDescriptionJdbcRepository.batchUpdateProductDetailDescriptions(entities);
	}

	private List<ProductDetailDescriptionEntity> getEntities(List<ProductDetailDescriptionCommand> productDetailDescriptionCommands){
		return productDetailDescriptionCommands.stream()
			.map(productDetailDescriptionStorageMapper::toEntity)
			.toList();
	}

}
