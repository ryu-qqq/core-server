package com.ryuqq.core.domain.product.dao.group;

import java.util.List;

import com.ryuqq.core.domain.product.core.ProductGroupCommand;

public interface ProductGroupPersistenceRepository {

	long save(ProductGroupCommand productGroupCommand);
	List<Long> saveAll(List<ProductGroupCommand> productGroupCommands);
	void update(ProductGroupCommand productGroupCommand);
	void updateAll(List<ProductGroupCommand> productGroupCommands);

}
