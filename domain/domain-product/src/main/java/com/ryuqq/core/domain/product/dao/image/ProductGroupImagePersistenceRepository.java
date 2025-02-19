package com.ryuqq.core.domain.product.dao.image;

import java.util.List;

public interface ProductGroupImagePersistenceRepository {

	void save(ProductGroupImageCommand productGroupImage);
	void saveAll(List<? extends ProductGroupImageCommand> productGroupImageCommands);
	void update(ProductGroupImageCommand productGroupImage);
	void updateAll(List<? extends ProductGroupImageCommand> productGroupImageCommands);

}
