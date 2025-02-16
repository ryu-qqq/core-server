package com.ryuqq.core.domain.product.dao.group;

import java.util.Optional;

import com.ryuqq.core.domain.product.dao.delivery.ProductDeliveryCommand;
import com.ryuqq.core.domain.product.dao.image.ProductDetailDescriptionCommand;
import com.ryuqq.core.domain.product.dao.image.ProductGroupImageContextCommand;
import com.ryuqq.core.domain.product.dao.notice.ProductNoticeCommand;
import com.ryuqq.core.domain.product.dao.options.mapping.ProductOptionContextCommand;
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
