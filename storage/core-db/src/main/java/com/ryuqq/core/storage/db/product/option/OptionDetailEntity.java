package com.ryuqq.core.storage.db.product.option;

import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "OPTION_DETAIL")
@Entity
public class OptionDetailEntity extends BaseEntity {

    @Column(name = "OPTION_GROUP_ID", nullable = false)
    private long optionGroupId;

    @Column(name = "OPTION_VALUE", length = 100, nullable = false)
    private String optionValue;

    protected OptionDetailEntity() {}

	public OptionDetailEntity(long optionGroupId, String optionValue) {
		this.optionGroupId = optionGroupId;
		this.optionValue = optionValue;
	}

	protected long getOptionGroupId() {
		return optionGroupId;
	}

	protected String getOptionValue() {
		return optionValue;
	}
}
