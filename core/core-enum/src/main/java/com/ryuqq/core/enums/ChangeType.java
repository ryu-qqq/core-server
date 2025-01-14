package com.ryuqq.core.enums;

import java.util.Arrays;

public enum ChangeType {

	ADDED,
	MODIFIED,
	;


	public static ChangeType of(String word){
		return Arrays.stream(ChangeType.values())
			.filter(changeType -> changeType.name().equals(word))
			.findFirst()
			.orElse(ChangeType.ADDED);
	}

}
