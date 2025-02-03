package com.ryuqq.core.storage.db.external;

import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "EXTERNAL_CATEGORY")
@Entity
public class ExternalCategoryEntity extends BaseEntity {

	@Column(name = "SITE_ID", nullable = false)
	private long siteId;

	@Column(name = "EXTERNAL_CATEGORY_ID", nullable = false)
	private String externalCategoryId;

	@Column(name = "EXTERNAL_CATEGORY_EXTRA_ID", nullable = true)
	private String externalCategoryExtraId;

	@Column(name = "DESCRIPTION", length = 100, nullable = true)
	private String description;

	@Column(name = "INTERNAL_CATEGORY_ID", nullable = false)
	private long internalCategoryId;

	protected ExternalCategoryEntity() {}

	public ExternalCategoryEntity(long siteId, String externalCategoryId, String externalCategoryExtraId,
								  String description, long internalCategoryId) {
		this.siteId = siteId;
		this.externalCategoryId = externalCategoryId;
		this.externalCategoryExtraId = externalCategoryExtraId;
		this.description = description;
		this.internalCategoryId = internalCategoryId;
	}
}
