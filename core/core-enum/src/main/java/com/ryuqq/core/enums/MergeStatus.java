package com.ryuqq.core.enums;

import java.util.Arrays;

public enum MergeStatus {
	OPENED,
	SYNCHRONIZE,
	CLOSED;

	public static MergeStatus of(String name){
		return Arrays.stream(MergeStatus.values())
			.filter(item -> item.name().equals(name))
			.findFirst()
			.orElse(OPENED);
	}

	public boolean isOpened(){
		return this.equals(OPENED);
	}
}
