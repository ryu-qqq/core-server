package com.ryuqq.core.storage.db.product.option;

import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "PRODUCT_OPTION")
@Entity
public class ProductOptionEntity extends BaseEntity {

	@Column(name = "PRODUCT_ID", nullable = false)
	private long productId;

	@Column(name = "OPTION_GROUP_ID", nullable = false)
	private long optionGroupId;

	@Column(name = "OPTION_DETAIL_ID", nullable = false)
	private long optionDetailId;

	protected ProductOptionEntity() {}

	public ProductOptionEntity(long productId, long optionGroupId, long optionDetailId) {
		this.productId = productId;
		this.optionGroupId = optionGroupId;
		this.optionDetailId = optionDetailId;
	}

	protected long getProductId() {
		return productId;
	}

	protected long getOptionGroupId() {
		return optionGroupId;
	}

	protected long getOptionDetailId() {
		return optionDetailId;
	}

}
