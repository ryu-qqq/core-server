package com.ryuqq.core.domain.product.dao.notice;

import com.ryuqq.core.domain.product.ProductNotice;

public class ProductNoticeCommandFactory {

	public static DefaultProductNoticeCommand createCommandFrom(ProductNotice notice) {
		return new DefaultProductNoticeCommand(
			notice.getProductGroupId(),
			notice.getMaterial(),
			notice.getColor(),
			notice.getSize(),
			notice.getMaker(),
			notice.getOrigin(),
			notice.getWashingMethod(),
			notice.getYearMonth(),
			notice.getAssuranceStandard(),
			notice.getAsPhone()
		);
	}

}
