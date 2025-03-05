package com.ryuqq.core.storage.db.product.group;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.Price;
import com.ryuqq.core.domain.product.dao.group.ProductGroupCommand;

@Component
public class ProductGroupStorageMapper {

	public ProductGroupEntity toEntity(ProductGroupCommand productGroupCommand){
		Price price = productGroupCommand.getPrice();

		if(productGroupCommand.id() > 0){
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
				price.getRegularPrice(),
				price.getCurrentPrice(),
				price.getSalePrice(),
				price.getDiscountRate(),
				productGroupCommand.soldOut(),
				productGroupCommand.displayed(),
				productGroupCommand.productStatus(),
				productGroupCommand.keyword()
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
			price.getRegularPrice(),
			price.getCurrentPrice(),
			price.getSalePrice(),
			price.getDiscountRate(),
			productGroupCommand.soldOut(),
			productGroupCommand.displayed(),
			productGroupCommand.productStatus(),
			productGroupCommand.keyword()
		);
	}


}
