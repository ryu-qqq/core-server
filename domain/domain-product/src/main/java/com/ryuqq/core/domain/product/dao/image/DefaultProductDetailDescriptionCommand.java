package com.ryuqq.core.domain.product.dao.image;

public record DefaultProductDetailDescriptionCommand(
	Long productGroupId,
	String detailDescription
) implements ProductDetailDescriptionCommand {

}
