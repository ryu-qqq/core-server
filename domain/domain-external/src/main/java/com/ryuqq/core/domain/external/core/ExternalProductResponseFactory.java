package com.ryuqq.core.domain.external.core;

import java.util.List;

import com.ryuqq.core.domain.external.ExternalProduct;

public class ExternalProductResponseFactory {

	public static List<ExternalProduct> mapResponse(
		String externalProductGroupId,
		List<? extends ExternalMallProductGroupRequestResponse.ProductIdMapping> productIdMappings) {

		return productIdMappings.stream()
			.map(pi ->
				ExternalProduct.create(
						externalProductGroupId,
						pi.getExternalProductId(),
						pi.getProductId(),
						pi.optionValue(),
						pi.getQuantity(),
						pi.getAdditionalPrice(),
						pi.soldOut(),
						pi.displayed(),
						pi.isDeleted()
			))
			.toList();

	}





}
