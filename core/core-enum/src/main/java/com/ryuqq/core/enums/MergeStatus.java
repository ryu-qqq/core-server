package com.ryuqq.core.enums;

import java.util.Arrays;

public enum MergeStatus {
	PENDING,
	APPROVED,
	REJECTED;

	public static MergeStatus of(String name){
		return Arrays.stream(MergeStatus.values())
			.filter(item -> item.name().equals(name))
			.findFirst()
			.orElse(PENDING);
	}
}
