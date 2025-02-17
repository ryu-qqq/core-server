package com.ryuqq.core.domain.product.dao.group;

import java.math.BigDecimal;

import com.ryuqq.core.domain.product.core.DefaultPrice;
import com.ryuqq.core.domain.product.core.Price;
import com.ryuqq.core.enums.ManagementType;
import com.ryuqq.core.enums.OptionType;
import com.ryuqq.core.enums.ProductCondition;
import com.ryuqq.core.enums.ProductStatus;

public record DefaultProductGroupCommand(long id, long sellerId, long categoryId, long brandId, String productGroupName,
										 String styleCode, ProductCondition productCondition,
										 ManagementType managementType, OptionType optionType,
										 BigDecimal regularPrice, BigDecimal currentPrice, boolean soldOut, boolean displayed,
										 ProductStatus productStatus, String keyword)

	implements ProductGroupCommand {

	@Override
	public Price getPrice() {
		return DefaultPrice.create(regularPrice, currentPrice);
	}

}
