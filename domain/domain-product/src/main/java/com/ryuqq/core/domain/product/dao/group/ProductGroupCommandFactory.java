package com.ryuqq.core.domain.product.dao.group;

import com.ryuqq.core.domain.product.ProductGroup;

public class ProductGroupCommandFactory {

	public static ProductGroupCommand createCommandFrom(ProductGroup productGroup) {
		if(productGroup.getId() != null){
			return new UpdateProductGroupCommand(
				productGroup.getId(),
				productGroup.getSellerId(),
				productGroup.getCategoryId(),
				productGroup.getBrandId(),
				productGroup.getProductGroupName(),
				productGroup.getStyleCode(),
				productGroup.getProductCondition(),
				productGroup.getManagementType(),
				productGroup.getOptionType(),
				productGroup.getRegularPrice(),
				productGroup.getCurrentPrice(),
				productGroup.getDiscountRate(),
				productGroup.isSoldOut(),
				productGroup.isDisplayed(),
				productGroup.getProductStatus(),
				productGroup.getKeyword()
			);
		}
		 return new CreateProductGroupCommand(
			productGroup.getSellerId(),
			productGroup.getCategoryId(),
			productGroup.getBrandId(),
			productGroup.getProductGroupName(),
			productGroup.getStyleCode(),
			productGroup.getProductCondition(),
			productGroup.getManagementType(),
			productGroup.getOptionType(),
			 productGroup.getRegularPrice(),
			 productGroup.getCurrentPrice(),
			productGroup.getDiscountRate(),
			productGroup.isSoldOut(),
			productGroup.isDisplayed(),
			productGroup.getProductStatus(),
			productGroup.getKeyword()
		);
	}
}
