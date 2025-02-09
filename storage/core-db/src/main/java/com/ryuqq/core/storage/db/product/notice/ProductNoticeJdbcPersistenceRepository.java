package com.ryuqq.core.storage.db.product.notice;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.product.core.ProductNoticeCommand;
import com.ryuqq.core.domain.product.dao.notice.ProductNoticePersistenceRepository;

@Repository
public class ProductNoticeJdbcPersistenceRepository implements ProductNoticePersistenceRepository {

	private final ProductNoticeStorageMapper productNoticeStorageMapper;
	private final ProductNoticeJdbcRepository productNoticeJdbcRepository;

	public ProductNoticeJdbcPersistenceRepository(ProductNoticeStorageMapper productNoticeStorageMapper,
												  ProductNoticeJdbcRepository productNoticeJdbcRepository) {
		this.productNoticeStorageMapper = productNoticeStorageMapper;
		this.productNoticeJdbcRepository = productNoticeJdbcRepository;
	}

	@Override
	public void save(ProductNoticeCommand productNoticeCommand){
		saveAll(List.of(productNoticeCommand));
	}

	@Override
	public void saveAll(List<ProductNoticeCommand> productNoticeCommands) {
		List<ProductNoticeEntity> entities = toEntities(productNoticeCommands);
		productNoticeJdbcRepository.batchInsertProductNotices(entities);
	}

	@Override
	public void update(ProductNoticeCommand productNoticeCommand) {
		updateAll(List.of(productNoticeCommand));
	}

	@Override
	public void updateAll(List<ProductNoticeCommand> productNoticeCommands) {
		List<ProductNoticeEntity> entities = toEntities(productNoticeCommands);
		productNoticeJdbcRepository.batchUpdateProductNotices(entities);
	}


	private List<ProductNoticeEntity> toEntities(List<ProductNoticeCommand> productNoticeCommands){
		return productNoticeCommands.stream()
			.map(productNoticeStorageMapper::toEntity)
			.toList();
	}



}
