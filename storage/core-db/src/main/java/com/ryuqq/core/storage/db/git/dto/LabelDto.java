package com.ryuqq.core.storage.db.git.dto;

import com.querydsl.core.annotations.QueryProjection;

public class LabelDto {
	private long id;
	private String name;

	@QueryProjection
	public LabelDto(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
