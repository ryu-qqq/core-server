package com.ryuqq.core.domain.product.dao.group;

import java.math.BigDecimal;

import com.ryuqq.core.domain.product.core.Price;
import com.ryuqq.core.enums.ManagementType;
import com.ryuqq.core.enums.OptionType;
import com.ryuqq.core.enums.ProductCondition;
import com.ryuqq.core.enums.ProductStatus;

public interface ProductGroupCommand {
	long id();
	String productGroupName();
	long sellerId();
	long categoryId();
	long brandId();
	String styleCode();
	ProductCondition productCondition();
	ManagementType managementType();
	OptionType optionType();
	Price getPrice();
	boolean soldOut();
	boolean displayed();
	ProductStatus productStatus();
	String keyword();

	static ProductGroupCommand of(long productGroupId, long sellerId, long categoryId, long brandId,
								  String productGroupName, String styleCode,
								  ProductCondition productCondition, ManagementType managementType,
								  OptionType optionType, BigDecimal regularPrice, BigDecimal currentPrice, BigDecimal salePrice,
								  boolean soldOut, boolean displayed, String keyword) {

		return new DefaultProductGroupCommand(productGroupId, sellerId, categoryId, brandId, productGroupName, styleCode,
			productCondition, managementType, optionType, regularPrice, currentPrice, salePrice, soldOut, displayed,
			ProductStatus.WAITING, keyword);
	}


}
