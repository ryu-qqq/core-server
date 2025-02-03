package com.ryuqq.core.domain.product.dao.options.mapping;

public record DefaultProductOptionCommand(
	long productId,
	long optionGroupId,
	long optionDetailId
) implements ProductOptionCommand{
}
