package com.ryuqq.core.storage.db.brand;

import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "BRAND")
@Entity
public class BrandEntity extends BaseEntity {


	@Column(name = "BRAND_NAME", nullable = false, length = 50)
	private String brandName;

	@Column(name = "BRAND_NAME_KR", nullable = false, length = 50)
	private String brandNameKr;

	@Column(name = "DISPLAYED", nullable = false)
	private boolean displayed;

	protected BrandEntity() {}

	public BrandEntity(String brandName, String brandNameKr, boolean displayed) {
		this.brandName = brandName;
		this.brandNameKr = brandNameKr;
		this.displayed = displayed;
	}


}
