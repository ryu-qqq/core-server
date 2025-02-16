package com.ryuqq.core.domain.product.dao.group;

import java.util.List;

public interface ProductGroupPersistenceRepository {

	long save(ProductGroupCommand productGroupCommand);
	List<Long> saveAll(List<ProductGroupCommand> productGroupCommands);
	void update(ProductGroupCommand productGroupCommand);
	void updateAll(List<ProductGroupCommand> productGroupCommands);

}
