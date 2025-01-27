package com.ryuqq.core.storage.db.product.option;

import com.ryuqq.core.enums.OptionName;
import com.ryuqq.core.storage.db.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Table(name = "OPTION_GROUP")
@Entity
public class OptionGroupEntity extends BaseEntity {

    @Column(name = "OPTION_NAME", length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private OptionName optionName;

    protected OptionGroupEntity() {}

	public OptionGroupEntity(OptionName optionName) {
		this.optionName = optionName;
	}

	public OptionName getOptionName() {
        return optionName;
    }

}
