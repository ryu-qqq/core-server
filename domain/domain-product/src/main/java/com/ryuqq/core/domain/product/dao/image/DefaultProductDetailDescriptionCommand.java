package com.ryuqq.core.domain.product.dao.image;

record DefaultProductDetailDescriptionCommand(
	long productGroupId,
	String detailDescription
) implements ProductDetailDescriptionCommand {

	public DefaultProductDetailDescriptionCommand assigned(long productGroupId){
		return new DefaultProductDetailDescriptionCommand(productGroupId, detailDescription);
	}

	@Override
	public ProductDetailDescriptionCommand assignProductGroupId(long productGroupId) {
		return new DefaultProductDetailDescriptionCommand(productGroupId, detailDescription);
	}

}
