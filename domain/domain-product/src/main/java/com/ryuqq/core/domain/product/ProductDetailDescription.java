package com.ryuqq.core.domain.product;

import java.util.Objects;

public class ProductDetailDescription {

	private final Long productGroupId;
	private final String detailDescription;

	private ProductDetailDescription(Long productGroupId, String detailDescription) {
		this.productGroupId = productGroupId;
		this.detailDescription = detailDescription;
	}

	public static ProductDetailDescription create(String detailDescription){
		return new ProductDetailDescription(null, detailDescription);
	}

	public static ProductDetailDescription create(Long productGroupId, String detailDescription){
		return new ProductDetailDescription(productGroupId, detailDescription);
	}

	public ProductDetailDescription assignProductGroupId(Long productGroupId) {
		return new ProductDetailDescription(productGroupId, detailDescription);
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
		ProductDetailDescription that = (ProductDetailDescription) object;
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
