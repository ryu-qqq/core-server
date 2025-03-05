package com.ryuqq.core.storage.db.product.image;


import com.querydsl.core.annotations.QueryProjection;

import com.ryuqq.core.enums.ProductImageType;

public class ProductGroupImageDto {

    private final long id;
    private final long productGroupId;
    private final ProductImageType productImageType;
    private final String imageUrl;
    private final String originUrl;
	private final int displayOrder;
	private final boolean deleted;

    @QueryProjection
    public ProductGroupImageDto(long id, long productGroupId, ProductImageType productImageType, String imageUrl, String originUrl,
								int displayOrder,
								boolean deleted) {
        this.id = id;
        this.productGroupId = productGroupId;
        this.productImageType = productImageType;
        this.imageUrl = imageUrl;
        this.originUrl = originUrl;
		this.displayOrder = displayOrder;
		this.deleted = deleted;
	}

    public long getId() {
        return id;
    }

    public long getProductGroupId() {
        return productGroupId;
    }

    public ProductImageType getProductImageType() {
        return productImageType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getOriginUrl() {
        return originUrl;
    }

	public int getDisplayOrder() {
		return displayOrder;
	}

	public boolean isDeleted() {
		return deleted;
	}
}
