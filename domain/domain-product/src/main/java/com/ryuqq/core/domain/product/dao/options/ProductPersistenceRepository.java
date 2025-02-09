package com.ryuqq.core.domain.product.dao.options;

import java.util.List;

import com.ryuqq.core.domain.product.core.ProductCommand;

public interface ProductPersistenceRepository {

	long save(ProductCommand productCommand);
	List<Long> saveAll(List<ProductCommand> productCommands);
	void update(ProductCommand productCommand);
	void updateAll(List<ProductCommand> productCommands);

}
