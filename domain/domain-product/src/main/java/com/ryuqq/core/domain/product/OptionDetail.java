package com.ryuqq.core.domain.product;

import java.util.Objects;

import com.ryuqq.core.domain.product.dao.options.detail.CreateOptionDetailCommand;
import com.ryuqq.core.domain.product.dao.options.detail.OptionDetailCommand;

public class OptionDetail {

	private final Long optionGroupId;
	private final String optionValue;

	private OptionDetail(Long optionGroupId, String optionValue) {
		this.optionGroupId = optionGroupId;
		this.optionValue = optionValue;
	}

	public static OptionDetail create(Long optionGroupId, String optionValue) {
		return new OptionDetail(optionGroupId, optionValue);
	}

	public OptionDetailCommand toCommand(){
		return new CreateOptionDetailCommand(optionGroupId, optionValue);
	}

	public String getOptionValue() {
		return optionValue;
	}

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		OptionDetail that = (OptionDetail) object;
		return Objects.equals(optionGroupId, that.optionGroupId)
			&& Objects.equals(optionValue, that.optionValue);
	}

	@Override
	public int hashCode() {
		return Objects.hash(optionGroupId, optionValue);
	}

	@Override
	public String toString() {
		return "OptionDetail{"
			+
			"optionGroupId="
			+ optionGroupId
			+
			", optionValue='"
			+ optionValue
			+ '\''
			+
			'}';
	}
}
