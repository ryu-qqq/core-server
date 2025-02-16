package com.ryuqq.core.domain.product.dao.notice;

import java.util.List;

public interface ProductNoticePersistenceRepository {

	void save(ProductNoticeCommand productNoticeCommand);
	void saveAll(List<ProductNoticeCommand> productNoticeCommands);
	void update(ProductNoticeCommand productNoticeCommand);
	void updateAll(List<ProductNoticeCommand> productNoticeCommands);

}
