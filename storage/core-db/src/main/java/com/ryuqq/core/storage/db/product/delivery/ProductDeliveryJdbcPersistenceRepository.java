package com.ryuqq.core.storage.db.product.delivery;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.product.dao.delivery.ProductDeliveryCommand;
import com.ryuqq.core.domain.product.dao.delivery.ProductDeliveryPersistenceRepository;

@Repository
public class ProductDeliveryJdbcPersistenceRepository implements ProductDeliveryPersistenceRepository {

	private final ProductDeliveryStorageMapper productDeliveryStorageMapper;
	private final ProductDeliveryJdbcRepository productDeliveryJdbcRepository;

	public ProductDeliveryJdbcPersistenceRepository(ProductDeliveryStorageMapper productDeliveryStorageMapper,
													ProductDeliveryJdbcRepository productDeliveryJdbcRepository) {
		this.productDeliveryStorageMapper = productDeliveryStorageMapper;
		this.productDeliveryJdbcRepository = productDeliveryJdbcRepository;
	}

	@Override
	public void save(ProductDeliveryCommand productDeliveryCommand){
		saveAll(List.of(productDeliveryCommand));
	}

	@Override
	public void saveAll(List<ProductDeliveryCommand> productDeliveryCommands) {
		List<ProductDeliveryEntity> entities = toEntities(productDeliveryCommands);
		productDeliveryJdbcRepository.batchInsertProductDeliveries(entities);

	}

	@Override
	public void update(ProductDeliveryCommand productDeliveryCommand) {
		updateAll(List.of(productDeliveryCommand));
	}

	@Override
	public void updateAll(List<ProductDeliveryCommand> productDeliveryCommands) {
		List<ProductDeliveryEntity> entities = toEntities(productDeliveryCommands);
		productDeliveryJdbcRepository.batchUpdateProductDeliveries(entities);
	}

	private List<ProductDeliveryEntity> toEntities(List<ProductDeliveryCommand> productDeliveryCommands){
		return productDeliveryCommands.stream()
			.map(productDeliveryStorageMapper::toEntity)
			.toList();
	}


}
