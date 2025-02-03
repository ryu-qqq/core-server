package com.ryuqq.core.domain.external.dao.group;

import com.ryuqq.core.domain.external.ExternalProductGroup;

public class ExternalProductGroupCommandFactory {

	public static ExternalProductGroupCommand toCommand(ExternalProductGroup externalProductGroup){
		return new CreateExternalProductGroupCommand(
			externalProductGroup.getSiteId(),
			externalProductGroup.getProductGroupId(),
			externalProductGroup.getExternalProductGroupId(),
			externalProductGroup.getBrandId(),
			externalProductGroup.getCategoryId(),
			externalProductGroup.getSellerId(),
			externalProductGroup.getProductName(),
			externalProductGroup.getRegularPrice(),
			externalProductGroup.getCurrentPrice(),
			externalProductGroup.getStatus(),
			externalProductGroup.isFixedPrice(),
			externalProductGroup.isSoldOut(),
			externalProductGroup.isDisplayed()
		);
	}
}
