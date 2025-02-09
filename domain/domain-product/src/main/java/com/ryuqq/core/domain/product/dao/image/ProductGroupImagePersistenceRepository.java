package com.ryuqq.core.domain.product.dao.image;

import java.util.List;

import com.ryuqq.core.domain.product.core.ProductGroupImageCommand;

public interface ProductGroupImagePersistenceRepository {

	void save(ProductGroupImageCommand productGroupImage);
	void saveAll(List<? extends ProductGroupImageCommand> productGroupImageCommands);
	void update(ProductGroupImageCommand productGroupImage);
	void updateAll(List<? extends ProductGroupImageCommand> productGroupImageCommands);

}
