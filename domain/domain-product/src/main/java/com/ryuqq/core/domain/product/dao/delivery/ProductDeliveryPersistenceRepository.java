package com.ryuqq.core.domain.product.dao.delivery;

import java.util.List;

import com.ryuqq.core.domain.product.core.ProductDeliveryCommand;

public interface ProductDeliveryPersistenceRepository {

	void save(ProductDeliveryCommand productDeliveryCommand);
	void saveAll(List<ProductDeliveryCommand> productDeliveryCommands);
	void update(ProductDeliveryCommand productDeliveryCommand);
	void updateAll(List<ProductDeliveryCommand> productDeliveryCommands);
}
