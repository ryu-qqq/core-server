package com.ryuqq.core.storage.db.product.option;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.dao.options.ProductCommand;

@Component
public class ProductStorageMapper {

	public ProductEntity toEntity(ProductCommand productCommand){
		if(productCommand.id() != null){
			return new ProductEntity(
				productCommand.id(),
				productCommand.productGroupId(),
				productCommand.soldOut(),
				productCommand.displayed(),
				productCommand.quantity(),
				productCommand.additionalPrice(),
				productCommand.deleted()
			);
		}

		return new ProductEntity(
			productCommand.productGroupId(),
			productCommand.soldOut(),
			productCommand.displayed(),
			productCommand.quantity(),
			productCommand.additionalPrice(),
			productCommand.deleted()
		);
	}




}
