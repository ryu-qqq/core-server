package com.ryuqq.core.storage.db.external;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.ExternalProductGroup;
import com.ryuqq.core.storage.db.external.dto.ExternalProductGroupDto;

@Component
public class ExternalProductGroupDomainMapper {

	public ExternalProductGroup toDomain(ExternalProductGroupDto externalProductGroupDto) {
		return ExternalProductGroup.create(
			externalProductGroupDto.getSiteId(),
			externalProductGroupDto.getSiteName(),
			externalProductGroupDto.getSellerName(),
			externalProductGroupDto.getProductGroupId(),
			externalProductGroupDto.getExternalProductGroupId(),
			externalProductGroupDto.getBrandId(),
			externalProductGroupDto.getExternalBrandId(),
			externalProductGroupDto.getCategoryId(),
			externalProductGroupDto.getExternalCategoryId(),
			externalProductGroupDto.getProductName(),
			externalProductGroupDto.getRegularPrice(),
			externalProductGroupDto.getCurrentPrice(),
			externalProductGroupDto.getStatus(),
			externalProductGroupDto.isFixedPrice(),
			externalProductGroupDto.isSoldOut(),
			externalProductGroupDto.isDisplayed()
		);
	}
}
