package com.ryuqq.core.storage.db.git.dto;

import com.querydsl.core.annotations.QueryProjection;

public class LabelDto {

	private String name;

	@QueryProjection
	public LabelDto(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
