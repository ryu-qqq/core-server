package com.ryuqq.core.storage.db.product.group;

import com.ryuqq.core.domain.product.DefaultProductGroup;

public class ProductGroupDomainMapper {

	public static DefaultProductGroup toDomain(ProductGroupDto dto) {
		return DefaultProductGroup.create(
			dto.getProductGroupId(),
			dto.getSellerId(),
			dto.getCategoryId(),
			dto.getBrandId(),
			dto.getProductGroupName(),
			dto.getStyleCode(),
			dto.getProductCondition(),
			dto.getManagementType(),
			dto.getOptionType(),
			dto.getRegularPrice(),
			dto.getCurrentPrice(),
			dto.isSoldOut(),
			dto.isDisplayed(),
			dto.getProductStatus(),
			dto.getKeywords()
		);
	}
}
