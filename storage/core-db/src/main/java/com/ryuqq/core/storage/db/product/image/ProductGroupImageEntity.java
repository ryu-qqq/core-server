package com.ryuqq.core.storage.db.product.image;

import com.ryuqq.core.enums.ProductImageType;
import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Table(name = "PRODUCT_GROUP_IMAGE")
@Entity
public class ProductGroupImageEntity extends BaseEntity {

	@Column(name = "PRODUCT_GROUP_ID", nullable = false)
	private long productGroupId;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "PRODUCT_GROUP_IMAGE_TYPE")
	private ProductImageType productImageType;

	@Column(name = "IMAGE_URL", length = 255, nullable = false)
	private String imageUrl;

	@Column(name = "ORIGIN_URL", length = 255, nullable = false)
	private String originUrl;

	@Column(name = "DISPLAY_ORDER", nullable = true)
	private int displayOrder;

	protected ProductGroupImageEntity() {}


	public ProductGroupImageEntity(long productGroupId, ProductImageType productImageType, String imageUrl, String originUrl, int displayOrder, boolean deleted) {
		this.productGroupId = productGroupId;
		this.productImageType = productImageType;
		this.imageUrl = imageUrl;
		this.originUrl = originUrl;
		this.displayOrder = displayOrder;
		this.deleted =deleted;
	}

	public ProductGroupImageEntity(long id, long productGroupId, ProductImageType productImageType, String imageUrl, String originUrl, int displayOrder, boolean deleted) {
		this.id = id;
		this.productGroupId = productGroupId;
		this.productImageType = productImageType;
		this.imageUrl = imageUrl;
		this.originUrl = originUrl;
		this.displayOrder = displayOrder;
		this.deleted =deleted;
	}

	protected long getProductGroupId() {
		return productGroupId;
	}

	protected ProductImageType getProductImageType() {
		return productImageType;
	}

	protected String getImageUrl() {
		return imageUrl;
	}

	protected String getOriginUrl() {
		return originUrl;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}
}
