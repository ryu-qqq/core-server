package com.ryuqq.core.storage.db.external;

import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "EXTERNAL_PRODUCT_SYNC_REQUEST")
@Entity
public class ExternalProductSyncRequestEntity extends BaseEntity {

	@Column(name = "TRACE_ID", nullable = false)
	private String traceId;

	@Column(name = "SITE_ID", nullable = false)
	private long siteId;

	@Column(name = "PRODUCT_GROUP_ID", nullable = false)
	private long productGroupId;

	@Column(name = "EXTERNAL_PRODUCT_GROUP_ID", nullable = true)
	private String externalProductGroupId;

	@Column(name = "SYNC_RESULT", nullable = false)
	private boolean syncResult;

	protected ExternalProductSyncRequestEntity() {}

	public ExternalProductSyncRequestEntity(String traceId, long siteId, long productGroupId,
											String externalProductGroupId, boolean syncResult) {
		this.traceId = traceId;
		this.siteId = siteId;
		this.productGroupId = productGroupId;
		this.externalProductGroupId = externalProductGroupId;
		this.syncResult = syncResult;
	}
}
