package com.ryuqq.core.domain.product;

import java.util.Objects;

import com.ryuqq.core.domain.product.dao.options.group.CreateOptionGroupCommand;
import com.ryuqq.core.domain.product.dao.options.group.OptionGroupCommand;
import com.ryuqq.core.enums.OptionName;

public class OptionGroup {

 	private final OptionName optionName;

	private OptionGroup(OptionName optionName) {
		this.optionName = optionName;
	}

	public static OptionGroup create(OptionName optionName) {
		return new OptionGroup(optionName);
	}

	public OptionGroupCommand toCommand(){
		return new CreateOptionGroupCommand(optionName);
	}

	public OptionName getOptionName() {
		return optionName;
	}

	@Override
	public boolean equals(Object object) {
		if (this
			== object) return true;
		if (object
			== null
			|| getClass()
			!= object.getClass()) return false;
		OptionGroup that = (OptionGroup) object;
		return optionName
			== that.optionName;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(optionName);
	}


	@Override
	public String toString() {
		return "OptionGroup{"
			+
			"optionName="
			+ optionName
			+
			'}';
	}
}
