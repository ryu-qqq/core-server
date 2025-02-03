package com.ryuqq.core.domain.product.dao.sync;

public interface ProductSyncPersistenceRepository {

	void save(ProductSyncCommand productSyncCommand);

}
