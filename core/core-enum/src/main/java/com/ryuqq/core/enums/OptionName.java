package com.ryuqq.core.enums;

public enum OptionName {
	SIZE,
	COLOR,
	DEFAULT_ONE,
	DEFAULT_TWO,
	;


	public boolean isSize(){
		return this.equals(SIZE);
	}

	public boolean isDefaultOne(){
		return this.equals(DEFAULT_ONE);
	}

	public boolean isDefaultTwo(){
		return this.equals(DEFAULT_TWO);
	}



}
