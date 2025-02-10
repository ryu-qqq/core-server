package com.ryuqq.core.domain.product.core;

import java.util.Objects;

public class DefaultProductDetailDescription implements ProductDetailDescription {

	private final Long productGroupId;
	private final String detailDescription;

	private DefaultProductDetailDescription(Long productGroupId, String detailDescription) {
		this.productGroupId = productGroupId;
		this.detailDescription = detailDescription;
	}

	public static DefaultProductDetailDescription create(String detailDescription){
		return new DefaultProductDetailDescription(null, detailDescription);
	}

	public static DefaultProductDetailDescription create(Long productGroupId, String detailDescription){
		return new DefaultProductDetailDescription(productGroupId, detailDescription);
	}

	public DefaultProductDetailDescription assignProductGroupId(Long productGroupId) {
		return new DefaultProductDetailDescription(productGroupId, detailDescription);
	}

	public Long getProductGroupId() {
		return productGroupId;
	}

	public String getDetailDescription() {
		return detailDescription;
	}

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		DefaultProductDetailDescription that = (DefaultProductDetailDescription) object;
		return Objects.equals(productGroupId, that.productGroupId)
			&& Objects.equals(detailDescription, that.detailDescription);
	}

	@Override
	public int hashCode() {
		return Objects.hash(productGroupId, detailDescription);
	}

	@Override
	public String toString() {
		return "ProductDetailDescription{"
			+
			"productGroupId="
			+ productGroupId
			+
			", detailDescription='"
			+ detailDescription
			+ '\''
			+
			'}';
	}
}
