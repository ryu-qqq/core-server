package com.ryuqq.core.storage.db.external;

import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "EXTERNAL_BRAND")
@Entity
public class ExternalBrandEntity extends BaseEntity {

	@Column(name = "SITE_ID", nullable = false)
	private long siteId;

	@Column(name = "EXTERNAL_BRAND_ID", nullable = false)
	private String externalBrandId;

	@Column(name = "INTERNAL_BRAND_ID", nullable = false)
	private long internalBrandId;

	protected ExternalBrandEntity() {}

	public ExternalBrandEntity(long siteId, String externalBrandId, long internalBrandId) {
		this.siteId = siteId;
		this.externalBrandId = externalBrandId;
		this.internalBrandId = internalBrandId;
	}

}
