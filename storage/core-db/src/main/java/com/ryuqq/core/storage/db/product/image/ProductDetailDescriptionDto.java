package com.ryuqq.core.storage.db.product.image;

import com.querydsl.core.annotations.QueryProjection;

public class ProductDetailDescriptionDto {
	private final long productGroupId;
    private final String detailDescription;


    @QueryProjection
    public ProductDetailDescriptionDto(long productGroupId, String detailDescription) {
		this.productGroupId = productGroupId;
		this.detailDescription = detailDescription;
    }

	public long getProductGroupId() {
		return productGroupId;
	}

	public String getDetailDescription() {
        return detailDescription;
    }

}
