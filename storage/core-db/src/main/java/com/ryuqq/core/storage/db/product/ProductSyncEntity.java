package com.ryuqq.core.storage.db.product;

import com.ryuqq.core.enums.SyncStatus;
import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Table(name = "PRODUCT_SYNC")
@Entity
public class ProductSyncEntity extends BaseEntity {

	@Column(name = "PRODUCT_GROUP_ID", nullable = false)
	private long productGroupId;

	@Column(name = "STATUS", nullable = false)
	@Enumerated(EnumType.STRING)
	private SyncStatus status;

	protected ProductSyncEntity() {}

	public ProductSyncEntity(long productGroupId, SyncStatus status) {
		this.productGroupId = productGroupId;
		this.status = status;
	}

	public ProductSyncEntity(long id, long productGroupId, SyncStatus status) {
		this.id = id;
		this.productGroupId = productGroupId;
		this.status = status;
	}

	public void setStatus(SyncStatus status) {
		this.status = status;
	}
}
