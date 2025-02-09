package com.ryuqq.core.domain.product.dao.image;

import java.util.List;

import com.ryuqq.core.domain.product.core.ProductDetailDescriptionCommand;

public interface ProductDetailDescriptionPersistenceRepository {

	void save(ProductDetailDescriptionCommand productDetailDescriptionCommand);
	void saveAll(List<ProductDetailDescriptionCommand> productDetailDescriptionCommands);
	void update(ProductDetailDescriptionCommand productDetailDescriptionCommand);
	void updateAll(List<ProductDetailDescriptionCommand> productDetailDescriptionCommands);

}
