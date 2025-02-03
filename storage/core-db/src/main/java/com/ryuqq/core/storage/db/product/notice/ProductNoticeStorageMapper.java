package com.ryuqq.core.storage.db.product.notice;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.dao.notice.ProductNoticeCommand;

@Component
public class ProductNoticeStorageMapper {

	public ProductNoticeEntity toEntity(ProductNoticeCommand productNoticeCommand){
		return new ProductNoticeEntity(
			productNoticeCommand.productGroupId(),
			productNoticeCommand.material(),
			productNoticeCommand.color(),
			productNoticeCommand.size(),
			productNoticeCommand.maker(),
			productNoticeCommand.origin(),
			productNoticeCommand.washingMethod(),
			productNoticeCommand.yearMonth(),
			productNoticeCommand.assuranceStandard(),
			productNoticeCommand.asPhone()
		);
	}


}
