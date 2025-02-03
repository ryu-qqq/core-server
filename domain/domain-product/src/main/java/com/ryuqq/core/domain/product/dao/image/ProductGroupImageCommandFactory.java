package com.ryuqq.core.domain.product.dao.image;

import com.ryuqq.core.domain.product.ProductGroupImage;

public class ProductGroupImageCommandFactory {

	public static ProductGroupImageCommand createCommandFrom(ProductGroupImage image) {
		if(image.getId() != null){
			return new CreateProductGroupImageCommand(
				image.getProductGroupId(),
				image.getProductImageType(),
				image.getImageUrl(),
				image.getOriginUrl(),
				image.isDeleted()
			);
		}

		return new UpdateProductGroupImageCommand(
			image.getId(),
			image.getProductGroupId(),
			image.getProductImageType(),
			image.getImageUrl(),
			image.getOriginUrl(),
			image.isDeleted()
		);
	}




}
