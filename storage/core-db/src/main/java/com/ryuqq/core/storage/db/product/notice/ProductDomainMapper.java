package com.ryuqq.core.storage.db.product.notice;

import com.ryuqq.core.domain.product.DefaultProductNotice;

public class ProductDomainMapper {

	public static DefaultProductNotice toDomain(ProductNoticeDto dto){
		return DefaultProductNotice.create(
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
