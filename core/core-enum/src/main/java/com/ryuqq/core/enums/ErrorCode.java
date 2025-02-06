package com.ryuqq.core.enums;

public enum ErrorCode {
	E200,
	E400,
	E404,
	E422,
	E500,
	E503;


	public boolean emergency(){
		return this.equals(E500) || this.equals(E503);
	}

}
