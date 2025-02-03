package com.ryuqq.core.domain.product;

import java.util.Objects;

import com.ryuqq.core.domain.product.dao.options.mapping.DefaultProductOptionCommand;
import com.ryuqq.core.domain.product.dao.options.mapping.ProductOptionCommand;


public class ProductOption {
	private final long productId;
	private final long optionGroupId;
	private final long optionDetailId;

	private ProductOption(long productId, long optionGroupId, long optionDetailId) {
		this.productId = productId;
		this.optionGroupId = optionGroupId;
		this.optionDetailId = optionDetailId;
	}

	public static ProductOption create(long productId, long optionGroupId, long optionDetailId) {
		return new ProductOption(productId, optionGroupId, optionDetailId);
	}


	public ProductOptionCommand toCommand(){
		return new DefaultProductOptionCommand(productId, optionGroupId, optionDetailId);
	}

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		ProductOption that = (ProductOption) object;
		return productId
			== that.productId
			&& optionGroupId
			== that.optionGroupId
			&& optionDetailId
			== that.optionDetailId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId, optionGroupId, optionDetailId);
	}

	@Override
	public String toString() {
		return "ProductOption{"
			+
			"productId="
			+ productId
			+
			", optionGroupId="
			+ optionGroupId
			+
			", optionDetailId="
			+ optionDetailId
			+
			'}';
	}
}
