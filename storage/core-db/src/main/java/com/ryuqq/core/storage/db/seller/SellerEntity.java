package com.ryuqq.core.storage.db.seller;

import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "SELLER")
@Entity
public class SellerEntity extends BaseEntity {

	@Column(name = "SELLER_NAME", nullable = false, length = 50)
	private String sellerName;

	protected SellerEntity() {}

	public SellerEntity(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getSellerName() {
		return sellerName;
	}
}
