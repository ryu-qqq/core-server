package com.ryuqq.core.domain.product.dao.options.detail;

public interface OptionDetailCommand {
	long optionGroupId();
	String optionValue();


	static OptionDetailCommand of(long optionGroupId, String optionValue){
		return new DefaultOptionDetailCommand(optionGroupId, optionValue);
	}


}
