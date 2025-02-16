package com.ryuqq.core.storage.db.product.image;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.dao.image.ProductGroupImageCommand;

@Component
public class ProductGroupImageStorageMapper {

	public ProductGroupImageEntity toEntity(ProductGroupImageCommand productGroupImageCommand){
		if(productGroupImageCommand.id() >0 ){
			return new ProductGroupImageEntity(
				productGroupImageCommand.id(),
				productGroupImageCommand.productGroupId(),
				productGroupImageCommand.productImageType(),
				productGroupImageCommand.imageUrl(),
				productGroupImageCommand.originUrl(),
				productGroupImageCommand.deleted()
			);
		}

		return new ProductGroupImageEntity(
			productGroupImageCommand.productGroupId(),
			productGroupImageCommand.productImageType(),
			productGroupImageCommand.imageUrl(),
			productGroupImageCommand.originUrl(),
			productGroupImageCommand.deleted()
		);
	}


}
