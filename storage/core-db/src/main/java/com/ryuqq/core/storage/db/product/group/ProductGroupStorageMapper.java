package com.ryuqq.core.storage.db.product.group;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.dao.group.ProductGroupCommand;

@Component
public class ProductGroupStorageMapper {

	public ProductGroupEntity toEntity(ProductGroupCommand productGroupCommand){
		if(productGroupCommand.id() != null){
			return new ProductGroupEntity(
				productGroupCommand.id(),
				productGroupCommand.sellerId(),
				productGroupCommand.categoryId(),
				productGroupCommand.brandId(),
				productGroupCommand.productGroupName(),
				productGroupCommand.styleCode(),
				productGroupCommand.productCondition(),
				productGroupCommand.managementType(),
				productGroupCommand.optionType(),
				productGroupCommand.regularPrice(),
				productGroupCommand.currentPrice(),
				productGroupCommand.discountRate(),
				productGroupCommand.soldOut(),
				productGroupCommand.displayed(),
				productGroupCommand.productStatus(),
				productGroupCommand.keywords()
			);
		}

		return new ProductGroupEntity(
			productGroupCommand.sellerId(),
			productGroupCommand.categoryId(),
			productGroupCommand.brandId(),
			productGroupCommand.productGroupName(),
			productGroupCommand.styleCode(),
			productGroupCommand.productCondition(),
			productGroupCommand.managementType(),
			productGroupCommand.optionType(),
			productGroupCommand.regularPrice(),
			productGroupCommand.currentPrice(),
			productGroupCommand.discountRate(),
			productGroupCommand.soldOut(),
			productGroupCommand.displayed(),
			productGroupCommand.productStatus(),
			productGroupCommand.keywords()
		);
	}


}
