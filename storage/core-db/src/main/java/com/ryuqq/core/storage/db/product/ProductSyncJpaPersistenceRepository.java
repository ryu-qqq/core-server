package com.ryuqq.core.storage.db.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ryuqq.core.domain.product.dao.sync.ProductSyncCommand;
import com.ryuqq.core.domain.product.dao.sync.ProductSyncPersistenceRepository;
import com.ryuqq.core.utils.TraceIdHolder;

@Repository
public class ProductSyncJpaPersistenceRepository implements ProductSyncPersistenceRepository {

	private final Logger log = LoggerFactory.getLogger(getClass());
	private final ProductSyncJpaRepository productSyncJpaRepository;

	public ProductSyncJpaPersistenceRepository(ProductSyncJpaRepository productSyncJpaRepository) {
		this.productSyncJpaRepository = productSyncJpaRepository;
	}

	@Override
	public void save(ProductSyncCommand productSyncCommand){
		productSyncJpaRepository.findByProductGroupId(productSyncCommand.productGroupId())
			.ifPresentOrElse(
				p -> {
					ProductSyncEntity productSyncEntity = new ProductSyncEntity(p.getId(),
						productSyncCommand.productGroupId(), productSyncCommand.status());

					productSyncJpaRepository.save(productSyncEntity);
				},
				() ->{
					log.error(String.format("Can't update productSyncEntity, trace id : %s, product group id : %d",
						TraceIdHolder.getTraceId(),
						productSyncCommand.productGroupId()));
				}
			);
	}

}
