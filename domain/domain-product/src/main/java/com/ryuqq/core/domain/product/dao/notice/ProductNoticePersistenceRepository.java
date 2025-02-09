package com.ryuqq.core.domain.product.dao.notice;

import java.util.List;

import com.ryuqq.core.domain.product.core.ProductNoticeCommand;

public interface ProductNoticePersistenceRepository {

	void save(ProductNoticeCommand productNoticeCommand);
	void saveAll(List<ProductNoticeCommand> productNoticeCommands);
	void update(ProductNoticeCommand productNoticeCommand);
	void updateAll(List<ProductNoticeCommand> productNoticeCommands);

}
