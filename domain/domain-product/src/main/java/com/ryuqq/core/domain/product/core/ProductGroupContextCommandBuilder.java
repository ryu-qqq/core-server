package com.ryuqq.core.domain.product.core;

import java.util.Optional;

import com.ryuqq.core.enums.OptionType;

public interface ProductGroupContextCommandBuilder {
	void withProductGroupId(long productGroupId);
	void withOptionType(OptionType optionGroup);
	void withProductGroupCommand(ProductGroupCommand productGroupCommand);
	void withProductNoticeCommand(ProductNoticeCommand productNoticeCommand);
	void withProductDeliveryCommand(ProductDeliveryCommand productDeliveryCommand);
	void withProductDetailDescriptionCommand(ProductDetailDescriptionCommand productDetailDescriptionCommand);
	void withProductGroupImageContextCommand(ProductGroupImageContextCommand productGroupImageContextCommand);
	void withProductContextCommand(ProductOptionContextCommand productOptionContextCommand);
	ProductGroupContextCommand build();

	Optional<Long> getProductGroupId();
	OptionType getOptionType();
}
