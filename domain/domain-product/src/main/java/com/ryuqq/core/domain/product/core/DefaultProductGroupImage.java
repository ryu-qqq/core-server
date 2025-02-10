package com.ryuqq.core.domain.product.core;

import java.util.Objects;

import com.ryuqq.core.enums.ProductImageType;

public class DefaultProductGroupImage implements ProductGroupImage {

	private final Long id;
	private final Long productGroupId;
	private final ProductImageType productImageType;
	private final String imageUrl;
	private final String originUrl;
	private final boolean deleted;

	private DefaultProductGroupImage(Long id, Long productGroupId, ProductImageType productImageType, String imageUrl,
									 String originUrl, boolean deleted) {
		this.id = id;
		this.productGroupId = productGroupId;
		this.productImageType = productImageType;
		this.imageUrl = imageUrl;
		this.originUrl = originUrl;
		this.deleted = deleted;
	}

	public static DefaultProductGroupImage create(ProductImageType productImageType, String imageUrl, String originUrl, boolean deleted){
		return new DefaultProductGroupImage(null, null, productImageType, imageUrl, originUrl, deleted);
	}

	public static DefaultProductGroupImage create(long id, long productGroupId, ProductImageType productImageType, String imageUrl, String originUrl, boolean deleted){
		return new DefaultProductGroupImage(id, productGroupId, productImageType, imageUrl, originUrl, deleted);
	}

	public DefaultProductGroupImage assignProductGroupId(Long productGroupId) {
		return new DefaultProductGroupImage(this.id, productGroupId, productImageType, imageUrl, originUrl, deleted);
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public long getProductGroupId() {
		return productGroupId;
	}

	@Override
	public ProductImageType getProductImageType() {
		return productImageType;
	}

	@Override
	public String getImageUrl() {
		return imageUrl;
	}

	@Override
	public String getOriginUrl() {
		return originUrl;
	}

	@Override
	public boolean isDeleted() {
		return deleted;
	}

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		DefaultProductGroupImage that = (DefaultProductGroupImage) object;
		return deleted
			== that.deleted
			&& Objects.equals(id, that.id)
			&& Objects.equals(productGroupId, that.productGroupId)
			&& productImageType
			== that.productImageType
			&& Objects.equals(imageUrl, that.imageUrl)
			&& Objects.equals(originUrl, that.originUrl);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, productGroupId, productImageType, imageUrl, originUrl, deleted);
	}

	@Override
	public String toString() {
		return "ProductGroupImage{"
			+
			"id="
			+ id
			+
			", productGroupId="
			+ productGroupId
			+
			", productImageType="
			+ productImageType
			+
			", imageUrl='"
			+ imageUrl
			+ '\''
			+
			", originUrl='"
			+ originUrl
			+ '\''
			+
			", deleted="
			+ deleted
			+
			'}';
	}
}
