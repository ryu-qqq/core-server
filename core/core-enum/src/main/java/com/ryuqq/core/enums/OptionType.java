package com.ryuqq.core.enums;


public enum OptionType {

	OPTION_ONE,
	OPTION_TWO,
	SINGLE;

	public boolean isMultiOption(){
		return !this.equals(SINGLE);
	}

	public boolean isSingleOption(){
		return this.equals(SINGLE);
	}

	public boolean isOneDepthOption(){
		return this.equals(OPTION_ONE);
	}

	public boolean isTwoDepthOption(){
		return this.equals(OPTION_TWO);
	}

}
