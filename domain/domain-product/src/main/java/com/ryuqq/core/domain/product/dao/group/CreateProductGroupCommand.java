package com.ryuqq.core.domain.product.dao.group;

import java.math.BigDecimal;

import com.ryuqq.core.enums.ManagementType;
import com.ryuqq.core.enums.OptionType;
import com.ryuqq.core.enums.ProductCondition;
import com.ryuqq.core.enums.ProductStatus;

public record CreateProductGroupCommand(
	long sellerId,
	long categoryId,
	long brandId,
	String productGroupName,
	String styleCode,
	ProductCondition productCondition,
	ManagementType managementType,
	OptionType optionType,
	BigDecimal regularPrice,
	BigDecimal currentPrice,
	int discountRate,
	boolean soldOut,
	boolean displayed,
	ProductStatus productStatus,
	String keywords

) implements ProductGroupCommand  {

	@Override
	public Long id() {
		return null;
	}

}
