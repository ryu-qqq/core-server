package com.ryuqq.core.domain.product;

import java.util.Objects;

import com.ryuqq.core.domain.product.core.ItemImage;
import com.ryuqq.core.enums.ProductImageType;

public class ProductGroupImage implements ItemImage {

	private final Long id;
	private final Long productGroupId;
	private final ProductImageType productImageType;
	private final String imageUrl;
	private final String originUrl;
	private final boolean deleted;

	private ProductGroupImage(Long id, Long productGroupId, ProductImageType productImageType, String imageUrl,
							 String originUrl, boolean deleted) {
		this.id = id;
		this.productGroupId = productGroupId;
		this.productImageType = productImageType;
		this.imageUrl = imageUrl;
		this.originUrl = originUrl;
		this.deleted = deleted;
	}

	public static ProductGroupImage create(ProductImageType productImageType, String imageUrl, String originUrl, boolean deleted){
		return new ProductGroupImage(null, null, productImageType, imageUrl, originUrl, deleted);
	}

	public static ProductGroupImage create(long id, long productGroupId, ProductImageType productImageType, String imageUrl, String originUrl, boolean deleted){
		return new ProductGroupImage(id, productGroupId, productImageType, imageUrl, originUrl, deleted);
	}

	public ProductGroupImage delete(){
		return new ProductGroupImage(id, productGroupId, productImageType, imageUrl, originUrl, true);
	}


	public ProductGroupImage assignProductGroupId(Long productGroupId) {
		return new ProductGroupImage(this.id, productGroupId, productImageType, imageUrl, originUrl, deleted);
	}


	public Long getId() {
		return id;
	}

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

	public String getOriginUrl() {
		return originUrl;
	}

	public boolean needsUpdate(String originUrl) {
		return !this.originUrl.equals(originUrl);
	}

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
		ProductGroupImage that = (ProductGroupImage) object;
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
