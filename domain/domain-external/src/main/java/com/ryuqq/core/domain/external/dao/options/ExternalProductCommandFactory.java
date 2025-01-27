package com.ryuqq.core.domain.external.dao.options;

import com.ryuqq.core.domain.external.ExternalProduct;

public class ExternalProductCommandFactory {

	public static ExternalProductCommand toCommand(ExternalProduct externalProduct){
		return new DefaultExternalProductCommand(
			externalProduct.getExternalProductGroupId(),
			externalProduct.getExternalProductId(),
			externalProduct.getProductId(),
			externalProduct.getOptionValue(),
			externalProduct.getQuantity(),
			externalProduct.getAdditionalPrice(),
			externalProduct.isSoldOut(),
			externalProduct.isDisplayed()
		);
	}
}
