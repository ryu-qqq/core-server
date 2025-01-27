package com.ryuqq.core.storage.db.product.notice;

import com.ryuqq.core.domain.product.ProductNotice;

public class ProductDomainMapper {

	public static ProductNotice toDomain(ProductNoticeDto dto){
		return ProductNotice.create(
			dto.getProductGroupId(),
			dto.getMaterial(),
			dto.getColor(),
			dto.getSize(),
			dto.getMaker(),
			dto.getOrigin(),
			dto.getWashingMethod(),
			dto.getYearMonth(),
			dto.getAssuranceStandard(),
			dto.getAsPhone()
		);
	}
}
