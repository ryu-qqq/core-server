package com.ryuqq.core.storage.db.external;

import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "EXTERNAL_CATEGORY_OPTION")
@Entity
public class ExternalCategoryOptionEntity extends BaseEntity {

	@Column(name = "SITE_ID", nullable = false)
	private Long siteId;

	@Column(name = "EXTERNAL_CATEGORY_ID", nullable = false)
	private String externalCategoryId;

	@Column(name = "OPTION_GROUP_ID", nullable = false)
	private long optionGroupId;

	@Column(name = "OPTION_DETAIL_ID", nullable = true)
	private long optionId;

	@Column(name = "OPTION_VALUE", nullable = false, length = 255)
	private String optionValue;

	public ExternalCategoryOptionEntity() {}

	public ExternalCategoryOptionEntity(Long siteId, String externalCategoryId, long optionGroupId, long optionId, String optionValue) {
		this.siteId = siteId;
		this.externalCategoryId = externalCategoryId;
		this.optionGroupId = optionGroupId;
		this.optionId = optionId;
		this.optionValue = optionValue;
	}

	public Long getSiteId() {
		return siteId;
	}

	public String getExternalCategoryId() {
		return externalCategoryId;
	}

	public long getOptionGroupId() {
		return optionGroupId;
	}

	public long getOptionId() {
		return optionId;
	}

	public String getOptionValue() {
		return optionValue;
	}
}
