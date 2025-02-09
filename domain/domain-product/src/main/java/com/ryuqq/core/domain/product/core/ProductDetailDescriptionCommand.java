package com.ryuqq.core.domain.product.core;

public interface ProductDetailDescriptionCommand {
	long productGroupId();
	String detailDescription();


	static ProductDetailDescriptionCommand of(long productGroupId, String detailDescription) {
		return new DefaultProductDetailDescriptionCommand(productGroupId, detailDescription);
	}

	ProductDetailDescriptionCommand assignProductGroupId(long productGroupId);

}
