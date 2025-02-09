package com.ryuqq.core.storage.db.product.image;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.ProductDetailDescriptionCommand;

@Component
public class ProductDetailDescriptionStorageMapper {

	public ProductDetailDescriptionEntity toEntity(ProductDetailDescriptionCommand productDetailDescriptionCommand){
		return new ProductDetailDescriptionEntity(
			productDetailDescriptionCommand.productGroupId(),
			productDetailDescriptionCommand.detailDescription()
		);
	}


}
