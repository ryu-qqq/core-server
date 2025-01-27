package com.ryuqq.core.domain.product.dao.options.mapping;

import java.util.List;

public interface ProductOptionPersistenceRepository {

	void save(ProductOptionCommand productOptionCommand);
	void saveAll(List<ProductOptionCommand> productOptionCommands);
	void update(ProductOptionCommand productOptionCommand);
	void updateAll(List<ProductOptionCommand> productOptionCommands);
	void deleteAll(List<Long> productIds);
}
