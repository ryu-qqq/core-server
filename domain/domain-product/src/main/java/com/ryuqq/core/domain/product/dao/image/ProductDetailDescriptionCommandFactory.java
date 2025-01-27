package com.ryuqq.core.domain.product.dao.image;

import com.ryuqq.core.domain.product.ProductDetailDescription;

public class ProductDetailDescriptionCommandFactory {

	public static ProductDetailDescriptionCommand createCommandFrom(ProductDetailDescription detailDescription) {
		return new DefaultProductDetailDescriptionCommand(
				detailDescription.getProductGroupId(),
				detailDescription.getDetailDescription()
		);
	}

}
