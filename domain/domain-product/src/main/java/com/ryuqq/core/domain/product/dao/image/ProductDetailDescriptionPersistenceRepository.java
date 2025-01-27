package com.ryuqq.core.domain.product.dao.image;

import java.util.List;

public interface ProductDetailDescriptionPersistenceRepository {

	void save(ProductDetailDescriptionCommand productDetailDescriptionCommand);
	void saveAll(List<ProductDetailDescriptionCommand> productDetailDescriptionCommands);
	void update(ProductDetailDescriptionCommand productDetailDescriptionCommand);
	void updateAll(List<ProductDetailDescriptionCommand> productDetailDescriptionCommands);

}
